package com.test;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Author：zhengqh
 * @date 2020/4/21 14:41
 * 消息处理中心
 **/
public class Broker {
    // 队列存储消息的最大数量
    private final static int MAX_SIZE =  3;
    // 保存消息数据的容器
    private static ArrayBlockingQueue<String> messageQueue =
            new ArrayBlockingQueue<String>(MAX_SIZE);
    // 生产消息
    public static void peoduce(String msg){
        if(messageQueue.offer(msg)){
            System.out.println("成功向消息处理中心投递消息："+
                    msg+" 当前消息总数："+messageQueue.size());
        }else{
            System.out.println("当前消息处理中心消息存放超载，暂不能放入消息！");
        }
        System.out.println("=======================");

    }
    // 消费消息
    public  static String consume(){
        String msg  =   messageQueue.poll();
        if(msg!=null){
            System.out.println("成功消费消息："+
                    msg+" 当前消息总数："+messageQueue.size());
        }else{
            System.out.println("暂无消息可消费！");
        }
        System.out.println("=======================");
        return msg;
    }

}
