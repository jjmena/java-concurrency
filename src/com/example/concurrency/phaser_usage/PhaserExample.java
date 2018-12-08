package com.example.concurrency.phaser_usage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.stream.Stream;

/**
 * Phaser is a structure similar to CountDownLatch mechanism to coordinate the execution of threads. Phaser is a barrier
 * which could be configured dynamically (CountDownLatch is only configurable in object creation).
 */
public class PhaserExample {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        try {
            Phaser phaser = new Phaser();
            List<Integer> values = new ArrayList<>();

            Stream.generate(() -> new Task(phaser, values))
                    .limit(10).forEach(executorService::submit);

            phaser.arriveAndAwaitAdvance();
            values.add(Integer.MAX_VALUE);

            System.out.println(String.format("Result after execution should show Integer Max values as last element of the list %s", values));

        } finally {
            executorService.shutdown();
        }
    }

    /**
     * Class Callable which receives Phaser and the list of elements to add Integer values.
     */
    public static class Task implements Callable<Void> {

        private final Phaser phaser;
        private final Random random;
        private final List<Integer> values;

        public Task(Phaser phaser, List<Integer> values) {
            this.phaser = phaser;
            this.random = new Random();
            this.values = values;
        }

        @Override
        public Void call() throws Exception {
            phaser.register();
            System.out.println(String.format("Starting thread %s", Thread.currentThread().getName()));
            int seconds = random.nextInt(10) + 10;
            values.add(seconds);
            Thread.sleep(seconds * 1000);
            phaser.arriveAndDeregister();
            System.out.println(String.format("Thread %s reach the Phaser Deregister. We are in %s arrived parties phase ", Thread.currentThread().getName(), phaser.getArrivedParties()));
            return null;
        }
    }
}
