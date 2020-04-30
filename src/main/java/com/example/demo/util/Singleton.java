package com.example.demo.util;

public class Singleton {
   /* private volatile static Singleton singleton;
    private Singleton (){};
    public static Singleton getInstance(){
        if (singleton==null){
            synchronized (Singleton.class){
                if (singleton==null){
                    singleton = new Singleton();
                }
            }
        }
        return  singleton;
    }*/
   private Singleton (){}
   private static  class SingletonHolder {
       private static final Singleton INSTANCE = new Singleton();
   }
    public static Singleton getInstance(){
       return SingletonHolder.INSTANCE;
    }
}
