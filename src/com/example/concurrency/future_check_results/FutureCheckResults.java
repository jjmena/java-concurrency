package com.example.concurrency.future_check_results;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Example about generate single thread executor to launch one task and receive the result.
 *
 * In this case we are going to check the future status before get it, and we are going th check the status after receive the result.
 *
 */
public class FutureCheckResults {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = null;
        try {
            executorService = Executors.newSingleThreadExecutor();

            // Runable lambdas
            Callable<List> loop = () -> IntStream.rangeClosed(0, 1000).mapToObj(i -> Integer.valueOf(i)).collect(Collectors.toList());

            // Submit the task
            Future<List> result = executorService.submit(loop);

            // Check the status and the result
            System.out.println("Before calling get: " + result.isDone());
            System.out.println(result.get());
            System.out.println("After calling get: " + result.isDone());


        } finally {
            if (executorService != null) executorService.shutdown();
        }
    }
}
