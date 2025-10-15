package com.baorant.resouceserverapplication.utils;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.net.URIBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

public class HttpClientWithBasicAuth {

    public HttpClientWithBasicAuth() {
    }

    /**
     * 手动构造Basic Auth认证头信息
     */
    private String getHeader(String userName, String password) {
        String auth = userName + ":" + password;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
        return "Basic " + new String(encodedAuth, StandardCharsets.UTF_8);
    }

    /**
     * 向指定的认证服务发送请求，以获取access token
     * @param url 认证服务的URL
     * @param userName 应用程序的Client_Id
     * @param password 应用程序的Client_secret
     * @param params 包含grant_type和scope的参数列表
     * @return 响应内容
     */
    public String send(String url, String userName, String password, Map<String, String> params) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            
            // 构建URL参数
            URIBuilder uriBuilder = new URIBuilder(url);
            if (params != null) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    uriBuilder.addParameter(entry.getKey(), entry.getValue());
                }
            }
            
            HttpPost post = new HttpPost(uriBuilder.build());
            post.addHeader("Authorization", getHeader(userName, password));
            post.addHeader("Content-Type", "application/x-www-form-urlencoded");
            
            try (CloseableHttpResponse response = client.execute(post)) {
                int statusCode = response.getCode();
                String responseContent = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                
                if (statusCode == 200) {
                    return responseContent;
                } else {
                    return "Error: HTTP " + statusCode + " - " + responseContent;
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}


