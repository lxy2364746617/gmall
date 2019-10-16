package com.lxy.gmall.list.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lxy.gmall.bean.SkuLsInfo;
import com.lxy.gmall.bean.SkuLsParam;
import com.lxy.gmall.bean.SkuLsResult;
import com.lxy.gmall.service.ListService;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.core.search.aggregation.MetricAggregation;
import io.searchbox.core.search.aggregation.TermsAggregation;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Chris
 * @data 2019-10-15 下午 3:26
 */
@Service
public class ListServiceImpl implements ListService {

    public static final String ES_INDEX = "gmall";

    public static final String ES_TYPE = "SkuInfo";

    @Autowired
    private JestClient jestClient;

    public void saveSkuInfo(SkuLsInfo skuLsInfo) {
        Index index = new Index.Builder(skuLsInfo).index(ES_INDEX).type(ES_TYPE).id(skuLsInfo.getId()).build();
        try {
            jestClient.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 动态生产dsl语句，从es中查找结果集
     * @param skuLsParam
     * @return
     */
    public SkuLsResult search(SkuLsParam skuLsParam) {
        String query = makeQueryStringForSearch(skuLsParam);
        Search search = new Search.Builder(query).addIndex(ES_INDEX).addType(ES_TYPE).build();
        SearchResult searchResult = null;
        try {
             searchResult = jestClient.execute(search);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SkuLsResult skuLsResult = makeResultForSearch(searchResult, skuLsParam);
        return skuLsResult;
    }

    /**
     * 设置返回结果
     * @param searchResult
     * @param skuLsParam
     * @return
     */
    private SkuLsResult makeResultForSearch(SearchResult searchResult, SkuLsParam skuLsParam) {
        SkuLsResult skuLsResult = new SkuLsResult();
        ArrayList<SkuLsInfo> skuLsInfoList = new ArrayList<>();
        List<SearchResult.Hit<SkuLsInfo, Void>> hits = searchResult.getHits(SkuLsInfo.class);
        if(hits != null && hits.size() > 0) {
            for (SearchResult.Hit<SkuLsInfo, Void> hit : hits) {
                SkuLsInfo skuLsInfo = hit.source;
                if(hit.highlight != null && hit.highlight.size() > 0) {
                    List<String> list = hit.highlight.get("skuName");
                    String skuNameHI = list.get(0);
                    skuLsInfo.setSkuName(skuNameHI);
                }
                skuLsInfoList.add(skuLsInfo);
            }
        }
        skuLsResult.setSkuLsInfoList(skuLsInfoList);

        skuLsResult.setTotal(searchResult.getTotal());

        long totalPage = (searchResult.getTotal() + skuLsParam.getPageSize() - 1) / skuLsParam.getPageSize();
        skuLsResult.setTotalPages(totalPage);

        MetricAggregation aggregations = searchResult.getAggregations();
        ArrayList<String> bucketList = new ArrayList<>();
        TermsAggregation term = aggregations.getTermsAggregation("groupby_attr_value");
        List<TermsAggregation.Entry> buckets = term.getBuckets();
        for(TermsAggregation.Entry entry : buckets) {
            String valueId = entry.getKey();
            bucketList.add(valueId);
        }
        skuLsResult.setAttrValueIdList(bucketList);
        return skuLsResult;
    }

    /**
     * 制作动态dsl语句
     * @param skuLsParam
     * @return
     */
    private String makeQueryStringForSearch(SkuLsParam skuLsParam) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        if(skuLsParam.getCatalog3Id() != null && skuLsParam.getCatalog3Id().length() > 0) {
            TermQueryBuilder termQueryBuilder = new TermQueryBuilder("catalog3Id", skuLsParam.getCatalog3Id());
            boolQueryBuilder.filter(termQueryBuilder);
        }

        if(skuLsParam.getValueId() != null && skuLsParam.getValueId().length > 0) {
            for(String valueId : skuLsParam.getValueId()) {
                TermQueryBuilder termQueryBuilder = new TermQueryBuilder("skuAttrValueList.valueId", valueId);
                boolQueryBuilder.filter(termQueryBuilder);
            }
        }

        if(skuLsParam.getKeyword() != null && skuLsParam.getKeyword().length() > 0) {
            MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("skuName", skuLsParam.getKeyword());
            boolQueryBuilder.must(matchQueryBuilder);

            HighlightBuilder highlighter = searchSourceBuilder.highlighter();
            highlighter.field("skuName");
            highlighter.preTags("<span style=color:red>");
            highlighter.postTags("</span>");

            searchSourceBuilder.highlight(highlighter);
        }

        searchSourceBuilder.query(boolQueryBuilder);

        searchSourceBuilder.sort("hostScore", SortOrder.DESC);

        int from = (skuLsParam.getPageNo() - 1) * skuLsParam.getPageSize();
        searchSourceBuilder.from(from);
        searchSourceBuilder.size(skuLsParam.getPageSize());

        TermsBuilder groupby_attr = AggregationBuilders.terms("groupby_attr_value").field("skuAttrValueList.valueId");
        searchSourceBuilder.aggregation(groupby_attr);

        String query = searchSourceBuilder.toString();
        return query;
    }

}
