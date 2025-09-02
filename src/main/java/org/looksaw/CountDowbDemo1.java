package org.looksaw;

import java.util.concurrent.CountDownLatch;

public class CountDowbDemo1 {
    private static class chef implements Runnable {
        private final String name;
        private final String dish;
        private final CountDownLatch latch;
        public chef(String name, String dish, CountDownLatch latch) {
            this.name = name;
            this.dish = dish;
            this.latch = latch;
        }
        @Override
        public void run() {
            try{
                System.out.println(name + " " + dish + " " + latch.getCount());
                Thread.sleep(1000);
                System.out.println(name + " " + dish + " " + latch.getCount() + " finished");
                latch.countDown();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int numOfChefs = 3;
        CountDownLatch latch = new CountDownLatch(numOfChefs);
        new Thread(new chef("chef1", "chef1", latch)).start();
        new Thread(new chef("chef2", "chef2", latch)).start();
        new Thread(new chef("chef3", "chef3", latch)).start();
        latch.await();
        System.out.println("main thread finished");
    }
}
