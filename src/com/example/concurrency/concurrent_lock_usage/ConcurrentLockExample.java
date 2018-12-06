package com.example.concurrency.concurrent_lock_usage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

/**
 * In this example we show an alternative to Synchronized block is using Lock.
 * <p>
 * This feature was introduces in Java 1.5 as an alternative to synchronized blocks to extends locking operations.
 * <p>
 * Some advantages of this approach:
 * <ul>
 * <li>This method provides a way to try to acquire lock rather than wait</li>
 * <li>This method provides us the possibility to interrupt locking</li>
 * <li>Be able to define explicitly lock/unlock operations to define the critical section</li>
 * <li>Be able to define two kind of usages for the lock: ReadLock, WriteLock, ReentrantLock</li>
 * </ul>
 *
 * In this example all threads don't get the lock. Only few of them got the lock.
 *
 * When all the threads have been finished we show the list of elements filled by those threads that got the lock.
 *
 */
public class ConcurrentLockExample {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Lock lock = new ReentrantLock();
        List<Integer> values = new ArrayList<>();

        Stream.generate(() -> new Task(lock, values))
                .limit(10)
                .forEach(t -> executorService.submit(t));

        executorService.shutdown();
        executorService.awaitTermination(100, TimeUnit.SECONDS);

        values.add(Integer.MAX_VALUE);

        System.out.println(String.format("Result after execution should show Integer Max values as last element of the list %s", values));

    }

    /**
     * Class Callable which receives CountDownLatch and the list of elements to add Integer values.
     */
    public static class Task implements Callable<Void> {

        private final Lock lock;
        private final Random random;
        private final List<Integer> values;

        public Task(Lock lock, List<Integer> values) {
            this.lock = lock;
            this.random = new Random();
            this.values = values;
        }

        @Override
        public Void call() throws Exception {

            System.out.println(String.format("Starting thread %s", Thread.currentThread().getName()));
            int seconds = random.nextInt(10);

            boolean isLockAcquire = lock.tryLock(20, TimeUnit.SECONDS);

            if (isLockAcquire) {
                values.add(seconds);
                Thread.sleep(seconds * 1000);
                System.out.println(String.format("Thread %s got the lock during %s seconds", Thread.currentThread().getName(), seconds));
                lock.unlock();
            } else {
                System.out.println(String.format("Thread %s didn't get the lock", Thread.currentThread().getName()));
            }

            return null;
        }
    }

}
