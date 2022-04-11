package com.doromv.store.controller;

import com.doromv.store.controller.ex.*;
import com.doromv.store.entity.User;
import com.doromv.store.service.IUserService;
import com.doromv.store.service.ex.InsertException;
import com.doromv.store.service.ex.UsernameDuplicatedException;
import com.doromv.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Doromv
 * @create 2022-03-30-9:22
 */
@RestController
@RequestMapping("/users")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;
//    @RequestMapping("/reg")
//    public JsonResult<Void> reg(User user){
//        JsonResult<Void> JsonResult = new JsonResult<>();
//        try {
//            userService.reg(user);
//            JsonResult.setState(200);
//            JsonResult.setMessage("用户注册成功");
//        } catch (UsernameDuplicatedException e) {
//           JsonResult.setState(4000);
//           JsonResult.setMessage("用户名被占用");
//        } catch (InsertException e) {
//            JsonResult.setState(5000);
//            JsonResult.setMessage("注册时发生未知的异常");
//        }
//        return JsonResult;
//    }
    @RequestMapping("/reg")
    public JsonResult<Void> reg(User user){
            userService.reg(user);
        return new JsonResult<>(OK);
    }
    @RequestMapping("/login")
    public JsonResult<User> login(String username,String password,HttpSession session){
        User data = userService.login(username, password);
        session.setAttribute("uid",data.getUid());
        session.setAttribute("username",data.getUsername());
        return new JsonResult<>(OK,data);
    }
    @RequestMapping("/change_password")
    public JsonResult<Void> changePassword(String oldPassword,String newPassword,HttpSession session){
        Integer uid=getUidFromSession(session);
        String username=getUsernameFromSession(session);
        userService.changePassword(uid,username,oldPassword,newPassword);
        return new JsonResult<>(OK);
    }
    @RequestMapping("/get_by_uid")
    public JsonResult<User> getByUid(HttpSession session){
        User data = userService.getByUid(getUidFromSession(session));
        return new JsonResult<>(OK,data);
    }
    @RequestMapping("/change_info")
    public JsonResult<Void> changeInfo(User user, HttpSession session){
        userService.changeInfo(getUidFromSession(session),getUsernameFromSession(session),user);
        return new JsonResult<>(OK);
    }
    public static final int AVATER_MAX_SIZE=10*1024*1024;
    public static final List<String> AVATER_TYPE=new ArrayList<>();
    static {
        AVATER_TYPE.add("image/jpeg");
        AVATER_TYPE.add("image/png");
        AVATER_TYPE.add("image/bmp");
        AVATER_TYPE.add("image/gif");
        AVATER_TYPE.add("image/jpg");
    }
    @RequestMapping("/change_avatar")
    public JsonResult<String> changeAvatar(HttpSession session, MultipartFile file){
        if(file.isEmpty()){
            throw new FileEmptyException("文件为空");
        }
        if(file.getSize()>AVATER_MAX_SIZE){
            throw new FileSizeException("文件大小超出限制");
        }
        String contentType = file.getContentType();
        if(!AVATER_TYPE.contains(contentType)){
            throw new FileTypeException("文件类型不支持");
        }
        String parent="E:\\IDEA 2021\\store项目\\upload";
        File dir=new File(parent);
        if(!dir.exists()){
            dir.mkdir();
        }
        String originalFilename = file.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        String filename=UUID.randomUUID().toString().toUpperCase()+suffix;
        File dest = new File(dir, filename);
        try {
            file.transferTo(dest);
        } catch (IOException ioException) {
            throw new FileUploadIOException("文件读写异常");
        } catch (FileStateException e){
            throw new FileStateException("文件状态异常");
        }
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        String avatar="/upload/"+filename;
        userService.changeAvatar(uid,avatar,username);
        return new JsonResult<>(OK,avatar);
    }
}
