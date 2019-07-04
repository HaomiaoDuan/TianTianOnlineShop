package com.tiantian.service;

import org.springframework.web.multipart.MultipartFile;

import com.tiantian.common.pojo.PictureResult;

public interface PictureService {

    public PictureResult uploadPic(MultipartFile picFile);
}
