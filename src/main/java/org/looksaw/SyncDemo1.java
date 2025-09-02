package org.looksaw;


public class SyncDemo1 {
    private synchronized static void incrementCounter() {
        counter++;
    }
    private static int counter = 0;
    public static void main(String[] args){
        //线程1
        Thread t1 = new Thread(() -> {
            for(int i = 0; i < 10000; i++){
                incrementCounter();
            }
        },"t1 thread");
        //线程2
        Thread t2 = new Thread(()-> {
            for(int i = 0; i < 10000; i++){
                incrementCounter();
            }
        },"t2 thread");


        try {
            t1.start();
            t2.start();
            t1.join();
            t2.join();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("counter is "+counter);
    }


}
