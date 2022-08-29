package org.ultimatecompbg;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        TimeoutCache newCacheMap = new TimeoutCache();
        ObjectKiller objectKiller = new ObjectKiller(newCacheMap); // Put this inside the TimeoutCache
        System.out.println("Object timeout(in milliseconds):");
        Scanner timeOut = new Scanner(System.in);
        int timeOutTime = timeOut.nextInt();
        objectKiller.setTime(timeOutTime);
        objectKiller.start();
        System.out.println(objectKiller.getState());

        while(true){
            Scanner scanner = new Scanner(System.in);
            String[] command = scanner.nextLine().split(" ");
            if(Objects.equals(command[0], "end")){
                objectKiller = null;
                break;
            }
            if(Objects.equals(command[0], "put")){
                newCacheMap.put(command[1]);
            }
            if(Objects.equals(command[0], "get")){
                System.out.println(newCacheMap.get(command[1]));
            }
            if(Objects.equals(command[0], "remove")){
                newCacheMap.remove(command[1]);
            }
            if(Objects.equals(command[0], "print")){
                newCacheMap.print();
            }
        }
    }
}
