package com.example.lesson.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
    /**
     * 访问webapp/jsp/index.jsp文件
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String index(){
        return "login";
    }

    /**
     * 访问webapp/jsp/index.jsp文件
     * @return
     */
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String test(){
        return "index";
    }
}
