package com.example.concurrency.countdownlatch_usage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

/**
 * Example of usage of CountDownLatch to synchronize several thread.
 * In this case we are going to synchronize 10 threads before add the last element to a list.
 * We are going to wait a random number of seconds and we will synchronize all of them
 */
public class CountDownLatchExample {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        try {
            CountDownLatch countDownLatch = new CountDownLatch(10);
            List<Integer> values = new ArrayList<>();

            Stream.generate(() -> new Task(countDownLatch, values))
                    .limit(10)
                    .forEach(t -> executorService.submit(t));

            countDownLatch.await();
            values.add(Integer.MAX_VALUE);

            System.out.println(String.format("Result after execution should show Integer Max values as last element of the list %s", values));

        } finally {
            if (executorService != null) {
                executorService.shutdown();
            }
        }
    }

    /**
     * Class Callable which receives CountDownLatch and the list of elements to add Integer values.
     */
    public static class Task implements Callable<Void> {

        private final CountDownLatch countDownLatch;
        private final Random random;
        private final List<Integer> values;

        public Task(CountDownLatch countDownLatch, List<Integer> values) {
            this.countDownLatch = countDownLatch;
            this.random = new Random();
            this.values = values;
        }

        @Override
        public Void call() throws Exception {

            System.out.println(String.format("Starting thread %s", Thread.currentThread().getName()));
            int seconds = random.nextInt(10) + 10;
            values.add(seconds);
            Thread.sleep(seconds * 1000);
            System.out.println(String.format("Thread %s reach the CountDown. We have %s threads to free the countdown latch", Thread.currentThread().getName(), countDownLatch.getCount()));
            countDownLatch.countDown();
            return null;
        }
    }
}
