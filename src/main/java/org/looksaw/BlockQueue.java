package org.looksaw;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockQueue {
    private static final int QUEUE_CAPACITY = 10;
    private static final BlockingQueue<Integer> taskQueue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);

    public static void main(String[] args){
        //生产者线程
        Thread producer = new Thread(()->{
            for(int i = 1; i <= 20; i++){
                try {
                    taskQueue.put(i);
                    System.out.println(" producer add " + i);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().isInterrupted();
                }
            }
        },"t1 producer");

        //消费者线程一
        Thread consumer1 = new Thread(()->{
            while(true){
                try {
                    int task = taskQueue.take();
                    System.out.println(" consumer add " + task + Thread.currentThread().getName());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().isInterrupted();
                }
            }
        },"t2 consumer1");

        //消费者二
        Thread consumer2 = new Thread(()->{
            while (true){
                try {
                    int task1 = taskQueue.take();
                    int task2 = taskQueue.take();
                    System.out.println(" consumer add " + task1 + Thread.currentThread().getName());
                    System.out.println(" consumer add " + task2 + Thread.currentThread().getName());
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"t3 consumer2");

        producer.start();
        consumer1.start();
        consumer2.start();

        try {
            producer.join();
            consumer1.join();
            consumer2.join();
        }catch (InterruptedException e){
            Thread.currentThread().isInterrupted();
        }
    }
}
