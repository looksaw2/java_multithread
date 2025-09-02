package org.looksaw;

import java.util.concurrent.locks.Lock;

public class WaitAndNotify1 {
    //锁
    private final static Object lock = new Object();
    //方法一
    private static void One() throws  InterruptedException {
        synchronized (lock){
            System.out.println("enter the one method");
            lock.wait();
            System.out.println("exit the one method again .......");
        }
    }
    //方法er
    private static void Two() throws InterruptedException {
        synchronized (lock){
            System.out.println("enter the two method");
            lock.notify();
            System.out.println("exit the two method again .......");
        }
    }

    //主函数
    public static void main(String[] args){
        Thread t1 = new Thread(() -> {
            try {
                One();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        },"t1 thread");
        Thread t2 = new Thread(() -> {
            try{
                Two();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"t2 thread");
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }

}
