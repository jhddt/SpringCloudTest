package com.baorant.userservice;

import com.baorant.userservice.entity.User;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

//@SpringBootTest
class UserServiceApplicationTests {

    @Test
    void contextLoads() {
        assertTrue(true); // 仅验证测试框架是否正常
    }

    @Test
    void testToJson() {
        User u = new User();
        u.setName("张三");
        
        u.setPassword("123456");
        u.setUsername("zhangsan");
        Gson gson = new Gson();
        String json = gson.toJson(u, User.class);
        System.out.print(json);
    }

    @Test
    void testFromJson() {
        String json ="{\"username\":\"zhangsan\",\"name\":\"张三\",\"password\":\"pass\"}";
        Gson gson = new Gson();
        User u = gson.fromJson(json, User.class);
        System.out.println("username="+u.getUsername());
        System.out.println("name="+u.getName());

    }
}
