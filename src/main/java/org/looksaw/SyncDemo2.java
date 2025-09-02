package org.looksaw;

//使用mutex锁
public class SyncDemo2 {
    private static int counter1 = 0;
    private static int counter2 = 0;
    private final static Object lock1 = new Object();
    private final static Object lock2 = new Object();

    private static void incrementCounter1() {
        synchronized (lock1){
            counter1++;
        }
    }

    private static void incrementCounter2() {
        synchronized (lock2){
            counter2++;
        }
    }


    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            incrementCounter1();
        },"t1 thread");
        Thread t2 = new Thread(() -> {
            incrementCounter2();
        },"t2 thread");

        t1.start();
        t2.start();
        try{
            t1.join();
            t2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(counter1);
        System.out.println(counter2);
    }
}
