package org.ultimatecompbg;

public class ObjectKiller extends Thread{ // Use Runnable
    private final TimeoutCache cacheMap;
    public ObjectKiller(TimeoutCache newCacheMap){
        cacheMap = newCacheMap;
    }
    private int timeOut = 0;
    public void setTime(int newTimeOut){
        timeOut = newTimeOut;
    }


    public void run(){
        System.out.println("fsijijjsoig");
        while(true){
            try{
                sleep(10);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
            cacheMap.checkAll(timeOut);
        }
    }

}
