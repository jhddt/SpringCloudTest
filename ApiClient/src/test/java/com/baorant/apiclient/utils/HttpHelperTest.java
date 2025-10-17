package com.baorant.apiclient.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HttpHelperTest {

    @Test
    void doGet() {
        String url = "http://192.168.56.101:4444/users/user/sayHi?sleep_seconds=1";
        String result = HttpHelper.doGet(url);
        assertTrue(result.contains("Hello"));
    }

    @Test
    void doPost() {

    }

    @Test
    void doPostJson() {
        String url = "http://192.168.56.101:10001/user/add";
        String data = "{" +	"    \"name\": \"李四1234\"," +
                "    \"password\": \"pass\"," +
                "    \"username\": \"lee111\"" +	"}";
        String result = HttpHelper.doPostJson(url, data);
        assertTrue(result.contains("true"));
    }
}