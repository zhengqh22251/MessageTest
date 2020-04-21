package com.test;

import java.io.IOException;

/**
 * @Author：zhengqh
 * @date 2020/4/21 15:23
 **/
public class ProduceClient {
    public static void main(String[] args) {
        MqClient mqClient =new MqClient();
        try {
            mqClient.produce("hello world");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
