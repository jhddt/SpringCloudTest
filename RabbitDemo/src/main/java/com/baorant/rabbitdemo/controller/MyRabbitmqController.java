package com.baorant.rabbitdemo.controller;

import com.baorant.rabbitdemo.component.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * RabbitMQ控制器类
 * 提供发送消息的HTTP接口
 */
@Controller
@RequestMapping("/rabbitmq")
public class MyRabbitmqController {
	 @Autowired
	 Sender sender;
	 
	    /**
	     * 发送消息的接口
	     * @return 返回发送状态信息
	     */
	    @RequestMapping("/send")
	    @ResponseBody
	    public String send(){
	        System.out.println("send string:hello world");
	        sender.send("hello world");
	        return "sending...";
	    }
}
