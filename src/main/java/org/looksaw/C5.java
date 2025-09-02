package org.looksaw;
//设置daemon线程
public class C5 {
    //线程1 Daemon
    private class DaemonThread implements Runnable {
        @Override
        public void run() {
            int count = 0;
            while(count < 1000){
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                count += 100;
                System.out.println("daemon counet is " + count);
            }
        }
    }
    //User线程
    private class UserThread2 implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("user thread 2 is end ......");
        }
    }
    //main
    public static void main(String[] args){
        var c = new C5();
        //
        Thread daemonThread = new Thread(c.new DaemonThread());
        Thread userThread = new Thread(c.new UserThread2());
        daemonThread.setDaemon(true);
        daemonThread.start();
        userThread.start();
    }
}
