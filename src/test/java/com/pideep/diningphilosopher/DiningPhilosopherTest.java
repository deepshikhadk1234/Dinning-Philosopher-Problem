package com.pideep.diningphilosopher;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {
    "spring.main.allow-bean-definition-overriding=true"
})
public class DiningPhilosopherTest {

    private static final int NUM_PHILOSOPHERS = 5;
    private static final int TEST_DURATION_SECONDS = 10;
    private Philosopher[] philosophers;
    private Chopstick[] chopsticks;
    private ExecutorService executorService;

    @BeforeEach
    void setUp() {
        philosophers = new Philosopher[NUM_PHILOSOPHERS];
        chopsticks = new Chopstick[NUM_PHILOSOPHERS];
        
        // Initialize chopsticks
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            chopsticks[i] = new Chopstick();
        }

        // Initialize philosophers with alternating chopstick pickup order
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            if (i % 2 == 0) {
                philosophers[i] = new Philosopher(i,
                        chopsticks[(i + 1) % NUM_PHILOSOPHERS],
                        chopsticks[i]);
            } else {
                philosophers[i] = new Philosopher(i,
                        chopsticks[i],
                        chopsticks[(i + 1) % NUM_PHILOSOPHERS]);
            }
        }

        executorService = Executors.newFixedThreadPool(NUM_PHILOSOPHERS);
    }

    @Test
    void testNoDeadlock() throws InterruptedException {
        // Start all philosophers
        for (Philosopher philosopher : philosophers) {
            executorService.execute(philosopher);
        }

        // Let the system run for a specified duration
        Thread.sleep(TEST_DURATION_SECONDS * 1000L);

        // Shutdown the executor service
        executorService.shutdown();
        
        // Wait for all tasks to complete
        boolean completed = executorService.awaitTermination(TEST_DURATION_SECONDS, TimeUnit.SECONDS);
        
        // Verify that all philosophers completed their execution without deadlock
        assertTrue(completed, "Test completed without deadlock");
    }

    @Test
    void testChopstickInitialization() {
        // Verify that chopsticks are properly initialized
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            assertNotNull(chopsticks[i], "Chopstick should not be null");
        }
    }

    @Test
    void testPhilosopherInitialization() {
        // Verify that philosophers are properly initialized
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            assertNotNull(philosophers[i], "Philosopher should not be null");
        }
    }

    @Test
    void testConcurrentExecution() throws InterruptedException {
        // Start all philosophers
        for (Philosopher philosopher : philosophers) {
            executorService.execute(philosopher);
        }

        // Let the system run for a specified duration
        Thread.sleep(TEST_DURATION_SECONDS * 1000L);

        // Shutdown the executor service
        executorService.shutdown();
        
        // Wait for all tasks to complete
        boolean completed = executorService.awaitTermination(TEST_DURATION_SECONDS, TimeUnit.SECONDS);
        
        // Verify that the system ran without deadlock
        assertTrue(completed, "All philosophers should have executed without deadlock");
    }

    @Test
    void testSinglePhilosopherExecution() throws InterruptedException {
        // Start a single philosopher
        executorService.execute(philosophers[0]);
        
        // Let the philosopher run for a short duration
        Thread.sleep(2000);
        
        // Shutdown the executor service
        executorService.shutdown();
        
        // Wait for the task to complete
        boolean completed = executorService.awaitTermination(2, TimeUnit.SECONDS);
        
        // Verify that the philosopher executed without issues
        assertTrue(completed, "Single philosopher should execute without issues");
    }
} 