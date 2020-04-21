package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @Author：zhengqh
 * @date 2020/4/21 15:15
 **/
public class MqClient {
    // 生产消息
    public void produce(String message) throws Exception {
        Socket socket =
                new Socket(InetAddress.getLocalHost(),BrokerServer.SERVICE_PORT);
        try(PrintWriter out = new PrintWriter(socket.getOutputStream())){
            out.println(message);
            out.flush();
        }

    }

    // 消费消息
    public  String consume() throws IOException {
        Socket socket =
                new Socket(InetAddress.getLocalHost(),BrokerServer.SERVICE_PORT);
        BufferedReader in =new BufferedReader(new InputStreamReader(socket.getInputStream()));
        try(PrintWriter out = new PrintWriter(socket.getOutputStream())){
            // 表示消费
            out.println("CONSUME");
            out.flush();
            // 再次从消息队列获取一条消息
            String message = in.readLine();
            return message;
        }
    }
}
