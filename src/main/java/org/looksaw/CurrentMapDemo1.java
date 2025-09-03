package org.looksaw;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CurrentMapDemo1 {
    private final static Map<String,String> cache = new ConcurrentHashMap<>();
    private static String compute(String key){
        System.out.println(key + "not present in the cache, so going to compute");
        try{
            Thread.sleep(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "Value of " + key;
    }

    private static String getCacheValue(String key){
        var result = cache.get(key);
        if( result == null){
            var rValue = compute(key);
            cache.put(key,rValue);
            return rValue;
        }
        return result;
    }

    public static void main(String[] args){
        for(int i  = 0; i < 10; i++){
            final int threadNum = i;
            new Thread(() -> {
                String key = "Key @ " + threadNum;
                for(int j = 0; j < 3; j++){
                    String val = getCacheValue(key);
                    System.out.println(key + ": " + val);
                }
            },"new a thread to start").start();
        }
    }
}
