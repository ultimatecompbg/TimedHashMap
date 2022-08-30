package org.ultimatecompbg;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static java.lang.Thread.sleep;

public class TimeoutCache implements Runnable {
    private int timeOut;
    public TimeoutCache(int newTimeOut){
        timeOut = newTimeOut;
    }
    private HashMap<String, HashMap<Object, Timestamp>> cacheMap = new HashMap<>();
    public void run()
    {
        // Checks for expired objects in the HashMap every 10 milliseconds
        while (true) {
            try{
                sleep(10);
            }
            catch(InterruptedException e){}
            Iterator<Map.Entry<String, HashMap<Object, Timestamp>>> iterator = cacheMap.entrySet().iterator();
            // Iterates through the HashMap
            while(iterator.hasNext()) {
                // If current time - next.timestamp > timeout, remove
                if(System.currentTimeMillis() - iterator.next().getValue().entrySet().stream().findFirst().get().getValue().getTime() >= timeOut){
                    iterator.remove();
                }
            }
        }

    }
    // Put object in the hashmap (key, <object, current time>)
    public void put(String key, Object object){
        HashMap<Object, Timestamp> newKeyValue = new HashMap<>();
        newKeyValue.put(object, new Timestamp(System.currentTimeMillis()));
        cacheMap.put(key, newKeyValue);
    }
    // get entry with key "string key"
    public Object get(String key){
        try{
            return cacheMap.get(key).entrySet().stream().findFirst().get().getKey();
        }
        catch(NullPointerException e){
            return null;
        }
        finally{
            if(cacheMap.get(key) != null){
                HashMap<Object, Timestamp> newKeyValue = new HashMap<>();
                newKeyValue.put(cacheMap.get(key).entrySet().stream().findFirst().get().getKey(), new Timestamp(System.currentTimeMillis()));
                cacheMap.put(key, newKeyValue);
            }

        }


    }
    // remove entry with key "string key"
    public void remove(String key){
        cacheMap.remove(key);
    }
}
