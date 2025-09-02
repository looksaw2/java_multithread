package org.looksaw;
//使用Thread来启动线程
public class C3 {
    //第一个线程
    private class Thread1 extends Thread {
        @Override
        public void run() {
            for(int i  = 0 ; i < 5 ; i++) {
                System.out.println("Thread1 started " + i);
            }
        }
    }
    //第二个线程
    private class Thread2 extends  Thread {
        @Override
        public void run() {
            for(int i = 0; i < 5 ; i++) {
                System.out.println("Thread2 started " + i);
            }
        }
    }

    //主函数
    public static void main(String[] args){
        var c3 = new C3();
        Thread t1 = new Thread(c3.new Thread1());
        Thread t2 = new Thread(c3.new Thread2());
        t1.start();
        t2.start();
    }
}
