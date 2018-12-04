package com.example.concurrency.volatile_usage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class VolatileExample {

    public static void main(String[] args) {
        CounterVolatile counterVolatile = new CounterVolatile();
        CounterNoVolatile counterNoVolatile = new CounterNoVolatile();

        // We are going to launch 1000 Threads to increment the value
        ExecutorService executorService = Executors.newFixedThreadPool(1000);

        try {

            IntStream.rangeClosed(1, 1000).forEach(i -> executorService.submit(counterVolatile::increment));
            System.out.println(String.format("Increment value with volatile %s", counterVolatile.getCounter()));

            IntStream.rangeClosed(1, 1000).forEach(i -> executorService.submit(counterNoVolatile::increment));
            System.out.println(String.format("Increment value without volatile %s", counterNoVolatile.getCounter()));


        } finally {
            if (executorService != null) {
                executorService.shutdown();
            }
        }

    }

    public static class CounterVolatile {
        private volatile int counter = 0;

        public void increment() {
            counter++;
        }

        public int getCounter() {
            return counter;
        }
    }

    public static class CounterNoVolatile {
        private int counter = 0;

        public void increment() {
            counter++;
        }

        public int getCounter() {
            return counter;
        }
    }

}
