package org.looksaw;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CacheThreadDemo1 {
    private static class Task implements Runnable {
        private int taskID;
        public Task(int taskID) {
            this.taskID = taskID;
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": " + taskID);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args){
        try(ExecutorService exector = Executors.newCachedThreadPool()){
            for(int i=0; i<1000; i++){
                exector.execute(new Task(i));
            }
        }
    }
}
