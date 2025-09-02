package org.looksaw;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class FutureAndCallable {
    private static class ReturnValue implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            return 12;
        }
    }


    public static void main(String[] args) throws Exception{
        try(var executor = Executors.newFixedThreadPool(2)){
            Future<Integer> result =  executor.submit(new ReturnValue());
            System.out.println(result.get(6, TimeUnit.MILLISECONDS));
        }
    }
}
