package org.ultimatecompbg;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        System.out.println("Object timeout(in milliseconds):");
        Scanner timeOut = new Scanner(System.in);
        int timeOutTime = timeOut.nextInt();
        TimeoutCache newCacheMap = new TimeoutCache(timeOutTime);
        Thread objectKiller = new Thread(newCacheMap);
        objectKiller.start();
        System.out.println(objectKiller.getState());

        while(true){
            Scanner scanner = new Scanner(System.in);
            String[] command = scanner.nextLine().split(" ");
            // Sets the thread to null and breaks the loop
            if(Objects.equals(command[0], "end")){
                objectKiller = null;
                break;
            }
            // Put new entry in the HashMap(command[1], <command[2], current time>)
            if(Objects.equals(command[0], "put")){
                newCacheMap.put(command[1], command[2]);
            }
            // Get entry with key "command[1]"
            if(Objects.equals(command[0], "get")){
                System.out.println(newCacheMap.get(command[1]));
            }
            // Remove entry with key "command[1]"
            if(Objects.equals(command[0], "remove")){
                newCacheMap.remove(command[1]);
            }
        }
    }
}
