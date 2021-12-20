package com.briup.cms.controller;

import com.briup.cms.service.UserService;
import com.briup.cms.utils.QiniuUtil;
import com.briup.cms.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Auther: vanse
 * @Date: 2021/12/20-12-20-15:13
 * @Description：com.briup.cms.controller
 * @version：1.0
 */
@RestController
@RequestMapping("/upload")
@Api(tags = "文件上传")
public class UploadController {
    @Autowired
    private UserService userService;
    @PostMapping("/image")
    public Result uploadImage(MultipartFile file) throws IOException {
       String image = QiniuUtil.upload2Qiniu(file.getBytes(), file.getOriginalFilename());
        return Result.success( QiniuUtil.BASE_URL+image);
    }
}
