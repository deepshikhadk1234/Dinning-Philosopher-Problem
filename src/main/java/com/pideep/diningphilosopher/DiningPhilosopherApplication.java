package com.pideep.diningphilosopher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class DiningPhilosopherApplication {
    static int no_of_philosophers=5;
    static Philosopher[] philosophers = new Philosopher[no_of_philosophers]; //thread
    static int no_of_chopsticks = 5;
    static Chopstick[] chopsticks = new Chopstick[no_of_chopsticks];//resource

    // philosopher has 2 actions: think & eat
    // Think: to put down both the chopsticks
    // Eat: to have both the chopsticks

    public static void main(String[] args) {
        SpringApplication.run(DiningPhilosopherApplication.class, args);
        int i;
        for(i=0;i<no_of_philosophers;i++) {
            chopsticks[i] = new Chopstick();
        }
        ExecutorService executorService = Executors.newFixedThreadPool(no_of_philosophers);
        for(i=0;i<no_of_philosophers;i++) {
            if(i%2 == 0)
            philosophers[i] = new Philosopher(i,
                    chopsticks[(i+1)%no_of_philosophers],
                                            chopsticks[i]
                                            );
            else{
                philosophers[i] = new Philosopher(i,
                        chopsticks[i],
                        chopsticks[(i+1)%no_of_philosophers]);
            }
            executorService.execute(philosophers[i]);
        }

    }

}
