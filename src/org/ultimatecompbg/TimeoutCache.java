package org.ultimatecompbg;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
//DA: Add come comments
public class TimeoutCache {
    private HashMap<String, Timestamp> cacheMap = new HashMap<>(); //DA: I want to put objects in the hashmap, not timestamp
    public void checkAll(int timeOut){
        Iterator<Map.Entry<String, Timestamp>> iterator = cacheMap.entrySet().iterator();
        while(iterator.hasNext()) {
            if(System.currentTimeMillis() - iterator.next().getValue().getTime() >= timeOut){
                iterator.remove();
            }
        }
    }
    public void print() {

    }
    public void put(String key){
        cacheMap.put(key, new Timestamp(System.currentTimeMillis()));
    }
    public Timestamp get(String key){
        try{
            return cacheMap.get(key);
        }finally{
            cacheMap.put(key, new Timestamp(System.currentTimeMillis()));
        }


    }
    public void remove(String key){
        cacheMap.remove(key);
    }
}
