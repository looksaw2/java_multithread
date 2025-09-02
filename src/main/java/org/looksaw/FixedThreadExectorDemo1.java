package org.looksaw;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadExectorDemo1 {
    private static class Worker implements Runnable {
        private int taskID;
        public Worker(int taskID) {
            this.taskID = taskID;
        }
        @Override
        public void run() {
            System.out.println("Executing task " + taskID + "is by" + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public static void main(String[] args){
        try(ExecutorService executor = Executors.newFixedThreadPool(2)){
            for(int i = 0; i < 8; i++){
                executor.execute(new Worker(i));
            }
        }
    }
}
