package org.looksaw;

//使用implements Runable来使用线程
public class C2 {
    //测试用的多线程一
    private class Thread1 implements  Runnable {

        @Override
        public void run() {
            for(int i = 0; i < 5; i++){
                System.out.println(Thread.currentThread().getName() + "This is " + i);
            }
        }
    }

    //测试用的多线程二
    private class Thread2 implements Runnable {

        @Override
        public void run() {
            for(int i = 0; i < 5; i++){
                System.out.println(Thread.currentThread().getName() + "This is " + i);
            }
        }
    }


    //主函数
    public static void main(String[] args){
        var c2 = new C2();
        //启动一个新的线程
        Thread t1 = new Thread( c2.new Thread1() );
        //启动另一个的线程
        Thread t2 = new Thread( c2.new Thread2() );
        //运行两个线程
        t1.start();
        t2.start();
    }
}



