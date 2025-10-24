package com.baorant.rabbitdemo.controller;

import com.baorant.rabbitdemo.component.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rabbitmq")
public class MyRabbitmqController {
	 @Autowired
	 Sender sender;
	 
	    @RequestMapping("/send")
	    @ResponseBody
	    public String send(){
	        System.out.println("send string:hello world");
	        sender.send("hello world");
	        return "sending...";
	    }
}
