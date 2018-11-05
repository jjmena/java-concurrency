package com.example.concurrency.executor_single_thread_with_result;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Example avout generate single thread executor to launch several tasks
 *
 * In this case we launch them in order.
 */
public class ExecutorServiceSingleThreadDemoWithResult {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = null;
        try {
            executorService = Executors.newSingleThreadExecutor();

            // Runnable lambdas
            Callable<List> loop = () -> IntStream.rangeClosed(0, 1000).mapToObj(i -> Integer.valueOf(i)).collect(Collectors.toList());
            Runnable message = () -> System.out.println("When this message will appear?");

            // Scheduled in Executors
            Future<?> result = executorService.submit(loop);
            executorService.execute(message);

            System.out.println("Before calling get: " + result.isDone());
            System.out.println(result.get());
            System.out.println("After calling get: " + result.isDone());


        } finally {
            if (executorService != null) executorService.shutdown();
        }
    }
}
