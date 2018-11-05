package com.example.concurrency.executor_single_thread_without_result;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * Example about generate single thread executor to launch several tasks
 *
 * In this case we launch them in order because we have a single thread service.
 */
public class ExecutorServiceSingleThreadDemo {

    public static void main(String[] args) {
        ExecutorService executorService = null;
        try {
            executorService = Executors.newSingleThreadExecutor();

            // Runnable lambdas
            Runnable loop = () -> IntStream.rangeClosed(0, 1000).forEach(System.out::println);
            Runnable message = () -> System.out.println("When this message will appear? It should appear at the end of the loop Runnable");

            // Scheduled in Executors
            executorService.execute(loop);
            executorService.execute(message);


        } finally {
            if (executorService != null) executorService.shutdown();
        }
    }
}
