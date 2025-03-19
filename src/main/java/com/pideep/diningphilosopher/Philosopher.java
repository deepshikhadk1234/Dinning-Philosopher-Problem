package com.pideep.diningphilosopher;

import java.util.concurrent.ThreadLocalRandom;

public class Philosopher implements Runnable {
    private int number;
    Chopstick left;
    Chopstick right;

    public Philosopher(int number, Chopstick left, Chopstick right) {
        this.number = number;
        this.left = left;
        this.right = right;
    }

    void performanceAction(String action){
        int waitTime = ThreadLocalRandom.current().nextInt(0,100);
        System.out.println("Philosopher " + (number+1) + " " + action +" "+ waitTime + "ms");
    }
    @Override
    public void run() {
        while(true){
            performanceAction("thinks for");
            left.pickUp();
            System.out.println("Philosopher " + (number+1) + " picks up left chopstick");
            right.pickUp();
            System.out.println("Philosopher " + (number+1) + " picks up right chopstick");
            performanceAction("eats for");
            left.dropDown();
            System.out.println("Philosopher " + (number+1) + " drops left chopstick");
            right.dropDown();
            System.out.println("Philosopher " + (number+1) + " drops right chopstick");

        }

    }
}
