package com.pideep.diningphilosopher;

import java.util.concurrent.Semaphore;

public class Chopstick {
    public Semaphore semaphore = new Semaphore(1);
    void pickUp(){
        try{
            semaphore.acquire();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    void dropDown(){
        semaphore.release();
    }

}
