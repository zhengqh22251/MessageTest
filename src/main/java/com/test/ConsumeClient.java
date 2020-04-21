package com.test;

import java.io.IOException;

/**
 * @Author：zhengqh
 * @date 2020/4/21 15:30
 **/
public class ConsumeClient {
    public static void main(String[] args) {
        MqClient mqClient =new MqClient();
        try {
            String message = mqClient.consume();
            System.out.println("获取到的消息为："+message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
