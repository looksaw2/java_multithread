package org.looksaw;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutor {
    private static class ProTask implements Runnable {

        @Override
        public void run() {
            System.out.println("Task executed");
        }
    }


    public static void main(String[] args){
        var executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(new ProTask(),1000,2000, TimeUnit.MILLISECONDS);
        try{
            if(!executor.awaitTermination(5000,TimeUnit.MILLISECONDS)){
                executor.shutdown();
            }
        }catch (InterruptedException e){
            executor.shutdownNow();
        }
    }
}
