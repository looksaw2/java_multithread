package org.looksaw;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class COWADemo1 {
    //读取打印的线程
    private static class ReadTask implements Runnable {
        private List<Integer> list;
        public ReadTask(List<Integer> list) {
            this.list = list;
        }
        @Override
        public void run() {
            while(true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(list);
            }
        }
    }
    //读取Task
    private static class WriteTask implements  Runnable {
        private  List<Integer> list;
        private Random random;
        public WriteTask(List<Integer> list) {
            this.list = list;
            this.random = new Random();
        }
        @Override
        public void run() {
            while(true){
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    throw new RuntimeException(e);
                }
                list.set(random.nextInt(list.size()), random.nextInt(10));
            }
        }
    }


    private static class Simulation {
        private final List<Integer> list;
        public Simulation() {
            this.list = new CopyOnWriteArrayList<>();
            list.addAll(Arrays.asList(0,0,0,0,0,0,0,0));
        }
        public void simulate() {
            Thread one = new Thread(new WriteTask(list),"t1 write thread");
            Thread two = new Thread(new WriteTask(list),"t2 write thread");
            Thread three = new Thread(new ReadTask(list),"t3 read thread");
            Thread four = new Thread(new ReadTask(list),"t4 read thread");
            one.start();
            two.start();
            three.start();
            four.start();
        }
    }

    public static void main(String[] args){
        Simulation simulation = new Simulation();
        simulation.simulate();
    }
}
