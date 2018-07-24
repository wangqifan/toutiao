package com.wangqifan.toutiao.Controller;

import com.wangqifan.toutiao.Models.User;
import com.wangqifan.toutiao.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class usercontroller {

    @Autowired
    private UserService userService;


}
