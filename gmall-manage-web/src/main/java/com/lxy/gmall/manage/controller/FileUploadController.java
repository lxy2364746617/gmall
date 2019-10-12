package com.lxy.gmall.manage.controller;

import org.apache.commons.lang3.StringUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Chris
 * @data 2019-10-11 下午 3:18
 */
@RestController
@CrossOrigin
public class FileUploadController {

    //获取服务器的IP地址
    @Value("${fileServer.url}")
    private String fileUrl;

    @RequestMapping("fileUpload")
    public String fileUpload(MultipartFile file) throws IOException, MyException {

        String imgUrl = fileUrl;

        if(file != null) {
            String configFile = this.getClass().getResource("/tracker.conf").getFile();
            ClientGlobal.init(configFile);
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageClient storageClient = new StorageClient(trackerServer, null);

            String originalFileName = file.getOriginalFilename();
            String extName = StringUtils.substringAfterLast(originalFileName, ".");

            String[] upload_file = storageClient.upload_file(file.getBytes(), extName, null);
            for (int i = 0; i < upload_file.length; i++) {
                String path = upload_file[i];
                //System.out.println("s = " + s);
                imgUrl += "/" + path;
            }
        }
        //return "http://192.168.111.110/group1/M00/00/00/wKhvbl2gJ6uANBPBAANGpinfz9o673.jpg";
        return imgUrl;
    }
}
