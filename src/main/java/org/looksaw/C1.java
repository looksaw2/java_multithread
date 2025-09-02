package org.looksaw;

public class C1 {
    //多线程测试一
    private void demo1(int n){
        for (int i  = 0 ; i < n; i++){
            System.out.println("From demo 1" + i);
        }
    }

    //多线程测试二
    private void demo2(int n){
        for (int i  = 0 ; i < n; i++){
            System.out.println("From demo 2" + i);
        }
    }
    //主要函数
    public static void main(String[] args){
        var c = new C1();
        final int N = 5;
        c.demo1(N);
        c.demo2(N);
    }

}
