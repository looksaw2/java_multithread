package org.looksaw;


//使用lambda函数来启动线程
public class C4 {
    public static void main(String[] args){
        Thread t1 = new Thread(() -> {
            for(int i = 0; i < 5; i++){
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        },"t1 Thread");
        Thread t2 = new Thread(()-> {
            for(int i = 0; i < 5; i++){
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        },"t2 Thread");

        t1.start();
        t2.start();
    }
}
