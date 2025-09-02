package org.looksaw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//线程安全的ArrayList
public class ConcurrencyList {
    public static void main(String[] args) {
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
        }, "t1 thread");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
        }, "t2 thread");
        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("list size is:" + list.size());
    }


}
