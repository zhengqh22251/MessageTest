package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author：zhengqh
 * @date 2020/4/21 14:50
 **/
public class BrokerServer implements Runnable {

    public static int SERVICE_PORT = 9999;
    private final Socket socket;

    public BrokerServer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader in =
                        new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out =
                        new PrintWriter(socket.getOutputStream())) {
            while (true) {
                String str = in.readLine();
                if (str == null) {
                    continue;
                }
                //System.out.println("接受到原始数据：" + str);
                if (str.equals("CONSUME")) { //表示要消费一条消息
                    String message = Broker.consume();
                    out.println(message);
                    out.flush();
                } else {
                    //其他情况都表示生产者生产消息
                    Broker.peoduce(str);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket= new ServerSocket(SERVICE_PORT);
        while(true){
            BrokerServer brokerServer=new BrokerServer(serverSocket.accept());
            new Thread(brokerServer).start();
        }
    }
}
