package com.tiantian.fastdfs;


import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

public class FastdfsTest {

    /*@Test
    public void testUpload() throws Exception{
       
        //1.把FastDFS提供的jar包导入工程
        //2.初始化全局配置，加载一个配置文件
        ClientGlobal.init("E:\\client.conf");
        //3.创建一个TrackerClient
        TrackerClient trackerClient = new TrackerClient();
        //4.创建一个TrackerServer
        TrackerServer trackerServer = trackerClient.getConnection();
        //5.声明一个StorageServer
        StorageServer storageServer = null;
        //6.获得StorageClient
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        //7.上传文件
        String[] strings = storageClient.upload_file("C:\\Users\\89371\\Pictures\\BigPower.jpg", "jpg", null);
        //打印结果
        for (String string : strings) {
            System.out.println(string);
        }
    }*/
    
    @Test
    public void TestFastDFSClient() throws Exception{
        FastDFSClient client = new FastDFSClient("E:\\client.conf");
        String uploadFile = client.uploadFile("C:\\Users\\89371\\Pictures\\Patrick.jpg","jpg");
        System.out.println(uploadFile);
    }
}
