package org.looksaw;

import java.util.ArrayList;
import java.util.List;

public class ProducerAndComsumer {
    private static  class Worker {
        private int sequence = 0;
        private final int top;
        private final int bottom;
        private List<Integer> container;
        private final Object lock = new Object();
        public Worker(int top, int bottom) {
            this.top = top;
            this.bottom = bottom;
            this.container = new ArrayList<Integer>();
        }
        //生产者
        public void produce() throws InterruptedException{
            synchronized (lock){
                while(true){
                    if(container.size() == top){
                        System.out.println("container size is top ,Please wait for the consumer consume the item ");
                        lock.wait();
                    }else{
                        System.out.println(sequence + "add to the container");
                        container.add(sequence++);
                        lock.notify();
                    }
                    Thread.sleep(1000);
                }
            }
        }
        //消费者
        public void consume() throws InterruptedException {
            synchronized (lock){
                while(true){
                    if(container.size() == bottom){
                        System.out.println("container size is bootom ,Please wait for the producer to produce the item ");
                        lock.wait();
                    }else {
                        System.out.println(container.removeFirst() + "remove from the container");
                        lock.notify();
                    }
                    Thread.sleep(1000);
                }
            }
        }
    }

    public static void main(String[] args){
        Worker work = new Worker(5,0);
        Thread t1 = new Thread(()->{
            try {
                work.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"t1 thread");
        Thread t2 = new Thread(()-> {
            try {
                work.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"t2 thread");
        t1.start();
        t2.start();

        try{
            t1.join();
            t1.join();
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}
