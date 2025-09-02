package org.looksaw;

import java.util.concurrent.Executors;

public class CpuTntensiveDemo1 {
    private static class CpuTask implements Runnable {

        @Override
        public void run() {
            System.out.println("CpuTask started" + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args){
        int core = Runtime.getRuntime().availableProcessors();
        var executor = Executors.newFixedThreadPool(core);
        System.out.println("CpuTask started" + Thread.currentThread().getName() + "with core " + core);
        for(int i = 0; i < 20; i++){
            executor.execute(new CpuTask());
        }
    }
}
