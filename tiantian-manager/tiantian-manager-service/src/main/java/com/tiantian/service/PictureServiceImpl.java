package com.tiantian.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tiantian.common.pojo.PictureResult;
import com.tiantian.common.utils.FastDFSClient;

@Service
public class PictureServiceImpl  implements PictureService{

    @Value("${IMAGE_SERVER_BASE_URL}")
    private String IMAGE_SERVER_BASE_URL;
    
    @Override
    public PictureResult uploadPic(MultipartFile picFile) {
        //1.创建PictureResult
        PictureResult result = new PictureResult();
        //2.判断图片是否是空
        if(picFile.isEmpty()){
            result.setError(1);
            result.setMessage("图片为空");
            return result;
        }
        
        //3.上传图片到服务器
        try {
            //获取文件全名
            String originalFilename = picFile.getOriginalFilename();
            //获取扩展名，不含"."
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
            //上传
            FastDFSClient client = new  FastDFSClient("classpath:properties/client.conf");
            String url = client.uploadFile(picFile.getBytes(),extName);
            //拼接图片服务器的ip地址
            url = IMAGE_SERVER_BASE_URL + url;
            //响应url
            result.setUrl(url);
            result.setError(0);
        } catch (Exception e) {
            result.setError(0);
            result.setMessage("上传失败");
            e.printStackTrace();
        }
        
       
        
        return result;
    }

}
