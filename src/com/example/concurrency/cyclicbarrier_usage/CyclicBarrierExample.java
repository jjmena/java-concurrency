package com.example.concurrency.cyclicbarrier_usage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

/**
 * Example of usage of CyclicBarrier. We are waiting to synchronize ten threads to free the barrier.
 *
 * CyclicBarrier is cyclic because we can reuse this object to use it again (for example calling reset method to re init it)
 *
 * In this example if we reduce the number of threads of the thread pool we will have an interlock due to we are waiting
 * for a barrier of 10 elements, but those threads haven't been free. We have lock every thread waiting to open the barrier
 * for all threads at the same time.
 */
public class CyclicBarrierExample {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        try {
            CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

            List<Integer> values = new ArrayList<>();

            Stream.generate(() -> new Task(cyclicBarrier, values))
                    .limit(10)
                    .forEach(t -> executorService.submit(t));
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

        private final CyclicBarrier cyclicBarrier;
        private final Random random;
        private final List<Integer> values;

        public Task(CyclicBarrier cyclicBarrier, List<Integer> values) {
            this.cyclicBarrier = cyclicBarrier;
            this.random = new Random();
            this.values = values;
        }

        @Override
        public Void call() throws Exception {

            System.out.println(String.format("Starting thread %s at %s", Thread.currentThread().getName(), LocalDateTime.now()));
            int seconds = random.nextInt(10) + 10;
            values.add(seconds);
            Thread.sleep(seconds * 1000);
            cyclicBarrier.await();
            System.out.println(String.format("Thread %s reach the CyclicBarrier. We have a delay of %s seconds in this thread at %s",
                    Thread.currentThread().getName(), seconds, LocalDateTime.now()));
            return null;
        }
    }
}
