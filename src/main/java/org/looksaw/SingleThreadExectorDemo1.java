package org.looksaw;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class SingleThreadExectorDemo1 {
    private static class Task implements  Runnable{
        private int taskID;
        public Task(int taskID){
            this.taskID = taskID;
        }
        @Override
        public void run() {
            System.out.println("Task with ID " + taskID + " executed" );
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args){
        try(var service = Executors.newSingleThreadExecutor()){
            for(int i = 0; i < 5; i++){
                service.execute(new Task(i));
            }
        }
    }
}
