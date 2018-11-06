package com.example.concurrency.executor_multi_thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * Example avout generate multiple threads executor to launch several tasks
 *
 * In this case we launch them mixed.
 */
public class ExecutorServiceMultiThreadDemo {

    public static void main(String[] args) {
        ExecutorService executorService = null;
        try {
            executorService = Executors.newFixedThreadPool(2);

            // Runnable lambdas
            Runnable loop = () -> {
                System.out.println("Starting first runnable");
                IntStream.rangeClosed(0, 1000).forEach(System.out::println);
                System.out.println("Finishing first runnable");
            };
            Runnable message = () -> System.out.println("When this message will appear? It should be shown in the middle of the printed numbers");

            // Scheduled in Executors
            executorService.execute(loop);
            executorService.execute(message);


        } finally {
            if (executorService != null) executorService.shutdown();
        }
    }
}
