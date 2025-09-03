package org.looksaw;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

//使用CyclicBarrier的Demo
public class CyclicBarrierDemo1 {
    private static final int NUM_TOURISTS = 5;
    private static final int NUM_STAGES = 3;
    //使用CyclicBarrier
    private static final CyclicBarrier barrier = new CyclicBarrier(NUM_TOURISTS,()->{
        System.out.println("All the threads is finished ..........");
    });
    //线程类
    private static class Tourist implements  Runnable {
        private final int touristID;
        public Tourist(int touristID) {
            this.touristID = touristID;
        }
        @Override
        public void run() {
            for(int i = 0; i < NUM_STAGES; i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(touristID + ": " + i);
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args){
        for(int i = 0; i < NUM_TOURISTS; i++){
            Thread t = new Thread(new Tourist(i));
            t.start();
        }
    }
}
