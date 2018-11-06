package com.example.concurrency.future_check_results_with_timeout;

import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class FutureCheckResultsWithTimeout {

    private static int counter = 0;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = null;
        try {
            executorService = Executors.newSingleThreadExecutor();

            // Generate a result after execution (but not result)
            Future<?> result = executorService.submit(() -> {
                Thread.sleep(Math.abs(new Random().nextInt() % 20_000));
                IntStream.range(0, 500).forEach(i -> FutureCheckResultsWithTimeout.counter++);
                return FutureCheckResultsWithTimeout.counter;
            });

            // Wait for ten seconds for the result
            Object counterReturned = result.get(10, TimeUnit.SECONDS);
            System.out.println("Reached!!  :D  -> " + counterReturned);
        } catch (TimeoutException e) {
            System.out.println("Not Reached!!! :( -> " + counter);
        } finally {
            if (executorService != null) executorService.shutdown();
        }
    }
}
