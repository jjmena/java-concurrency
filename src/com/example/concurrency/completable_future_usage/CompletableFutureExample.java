package com.example.concurrency.completable_future_usage;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * CompletableFuture implements two existing interfaces: Future and CompletionStage.
 * <p>
 * In this example we generate 10 Tasks which are going to wait different amount of time.
 * We are going to wait to all threads completed to show the result
 * <p>
 * As a second step we are going to show the way to deal with a response of a asynchronous operation, and after that
 * we are going to do any kind of calculation (in the example we calculate square value).
 */
public class CompletableFutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        try {

            // First part synchronize several tasks launched asynchronously.
            List<CompletableFuture<Integer>> completables = IntStream.range(0, 10).mapToObj(i -> CompletableFuture
                    .supplyAsync(new Task(), executorService))
                    .collect(Collectors.toList());

            CompletableFuture<List<Integer>> completableResult = CompletableFuture.allOf(completables
                    .toArray(new CompletableFuture[0]))
                    .thenApply(__ -> completables.stream()
                            .map(CompletableFuture::join)
                            .collect(Collectors.toList()));

            System.out.println(String.format("The list of times expended %s", completableResult.get()));

            // Second Part Wait for a task to launch a second one
            CompletableFuture<Integer> result = CompletableFuture.supplyAsync(new Task())
                    .thenApply(i -> i * i);

            System.out.println(String.format("The square value of the number of seconds waited: %s", result.get()));

        } finally {
            executorService.shutdown();
        }
    }

    /**
     * Class Callable to generate a number on seconds, but before return it we are going to wait this amount of seconds.
     */
    public static class Task implements Supplier<Integer> {

        private final static Random random = new Random();

        @Override
        public Integer get() {
            System.out.println(String.format("Starting thread %s at %s", Thread.currentThread().getName(), LocalDateTime.now()));
            Integer seconds = random.nextInt(10) + 10;
            try {
                Thread.sleep(seconds * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return Integer.MIN_VALUE;
            }
            System.out.println(String.format("Thread %s reach the time waiting. We have a delay of %s seconds in this thread at %s",
                    Thread.currentThread().getName(), seconds, LocalDateTime.now()));
            return seconds;
        }
    }
}

