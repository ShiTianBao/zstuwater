package com.stb.zstuwater.controller;

import com.stb.zstuwater.common.CommonMsg;
import com.stb.zstuwater.common.GlobalConstant;
import com.stb.zstuwater.persistent.entity.Admin;
import com.stb.zstuwater.persistent.repository.AdminRepository;
import com.stb.zstuwater.unit.MD5Utils;
import com.stb.zstuwater.unit.RSAUtils;
import com.stb.zstuwater.unit.VerifyCode;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;

@Controller
public class IndexController implements GlobalConstant {

    @Resource
    AdminRepository adminRepository;

    /**
     * 生成公钥
     * https://blog.csdn.net/weixin_42127766/article/details/82802189
     * @param request
     * @return
     */
    @RequestMapping(value="/public-key", method = RequestMethod.POST)
    @ResponseBody
    public String getKey(HttpServletRequest request) {
        String publicKey = RSAUtils.generateBase64PublicKey();
        return publicKey;
    }

    @RequestMapping("/")
    public ModelAndView index(HttpSession session) {
        if(null == session.getAttribute(SESSION_ATTR_KEY)) {
            return new ModelAndView("index");
        }
        return home(session);
    }

    @RequestMapping("/home")
    public ModelAndView home(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("admin", session.getAttribute(SESSION_ATTR_KEY));
        return modelAndView;
    }

    @RequestMapping("/login")
    @ResponseBody
    public CommonMsg login(HttpSession session, Admin admin, String code) {
        System.out.println(admin.toString());
        System.out.println("code:::" + code);
        CommonMsg cm = new CommonMsg();
        String name = RSAUtils.decryptBase64(admin.getAccount());

        String text = (String) session.getAttribute(SESSION_VERIFY_KEY);
        if(!text.toLowerCase().equals(code.toLowerCase())) {
            cm.setMsg("验证码错误");
            return cm;
        }

        //将用户密码解密后再按MD5加密
        admin.setAccount(MD5Utils.md5(RSAUtils.decryptBase64(admin.getAccount())));
        admin.setPassword(MD5Utils.md5(RSAUtils.decryptBase64(admin.getPassword())));

        System.out.println("account:::::" + admin.getAccount());
        System.out.println("password:::::" + admin.getPassword());

        Admin verifyAdmin = new Admin();
        verifyAdmin.setAccount(admin.getAccount());
        Optional<Admin> adminOptional = adminRepository.findOne(Example.of(verifyAdmin));
        if(!adminOptional.isPresent()) {
            cm.setMsg("用户名不存在");
            return cm;
        }
        verifyAdmin = adminOptional.get();
        if(!verifyAdmin.getPassword().equals(admin.getPassword())) {
            cm.setMsg("密码错误");
            return cm;
        }
        cm.setMsg("success");
        verifyAdmin.setAccount(name);
        session.setAttribute(SESSION_ATTR_KEY, verifyAdmin);
        return cm;
    }

    @RequestMapping("/quit")
    public String quit(HttpSession session) {
        session.removeAttribute(SESSION_ATTR_KEY);
        return "redirect:/";
    }

    @RequestMapping("/verify")
    public void verifyCode(HttpSession session, HttpServletResponse response) {
        //创建对象
        VerifyCode vc = new VerifyCode();
        //获取图片对象
        BufferedImage image = vc.getImage();
        //获取图片的文本内容
        String text = vc.getText();
        //将系统生成的文本内容保存到session中
        session.setAttribute(SESSION_VERIFY_KEY, text);
        //向浏览器输出图片
        try{
            OutputStream out = response.getOutputStream();
            vc.output(image, out);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
