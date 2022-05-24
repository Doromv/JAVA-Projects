package com.doromv.controller;

import com.doromv.utils.ResponseResult;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * 进行文件的上传和下载
 * @author Doromv
 * @create 2022-05-24-14:07
 */
@RestController
@RequestMapping("/common")
public class CommonController {

    @Value("${order.path}")
    private String basePath;
    /**
     *  接收图片
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public ResponseResult<String> uploadImage(MultipartFile file){
        //1.截取原始文件后缀
        String suffix = file.getOriginalFilename()
                .substring(file.getOriginalFilename().lastIndexOf("."));
        //2.生成UUID作为文件名
        String UUIDName = UUID.randomUUID().toString();
        //3.拼接文件名和后缀
        String fileName = UUIDName + suffix;
        //4.创建存储的目录对象
        File saveDir = new File(basePath);
        //5.判断目录是否存在，不存在则创建
        if(!saveDir.exists()){
            saveDir.mkdirs();
        }
        try {
            file.transferTo(new File(basePath+fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseResult.success(fileName);
    }

    @GetMapping("/download")
    public void downloadImage(@RequestParam("name") String fileName, HttpServletResponse response){

        //利用common-io进行文件传输
        response.setContentType("image/jpeg");
        try(
                FileInputStream fileInputStream =
                        new FileInputStream(basePath + fileName);
                ServletOutputStream fileOutputStream = response.getOutputStream();
        ) {
            IOUtils.copy(fileInputStream,fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
