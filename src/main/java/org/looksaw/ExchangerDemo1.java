package org.looksaw;

import java.util.concurrent.Exchanger;

//exchanger用于两个线程之间交换线程
public class ExchangerDemo1 {
    //第一个线程
    private static class FirstThread implements Runnable {
        private final Exchanger<Integer> exchanger;
        public FirstThread(Exchanger<Integer> exchanger) {
            this.exchanger = exchanger;
        }
        @Override
        public void run() {
            //需要交换的数据
            int dataSend = 10;
            System.out.println("First thread sending data is " + dataSend);
            //阻塞，得到线程二传过来的数据
            try {
                Integer result = exchanger.exchange(dataSend);
                System.out.println("First thread receive data is " + result);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    //线程二
    private static class SecondThread implements Runnable {
        private final Exchanger<Integer> exchanger;
        public SecondThread(Exchanger<Integer> exchanger) {
            this.exchanger = exchanger;
        }
        @Override
        public void run() {
            int dataSend = 20;
            System.out.println("Second thread sending data is " + dataSend);
            try {
                Thread.sleep(1000);
                //得到线程一的数据
                Integer result = exchanger.exchange(dataSend);
                System.out.println("Second thread receive data is " + result);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    //
    public static void main(String[] args){
        //初始化一个Exchanger
        Exchanger<Integer> exchanger = new Exchanger<>();
        //初始化两个线程
        Thread t1 = new Thread(new FirstThread(exchanger),"t1 thread");
        Thread t2 = new Thread(new SecondThread(exchanger),"t2 thread");
        t1.start();
        t2.start();
    }
}
