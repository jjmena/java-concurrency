package com.example.concurrency.executor_awaiting_for_all_tasks;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * In this example we can see how we can wait for tasks termination using <b>awaitTermination</b>
 */
public class ExecutorAwaitingForAllTasks {


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(20);

        // We are going to launch 100 tasks
        try {
            System.out.println("Scheduling Tasks");
            IntStream.range(0, 100).forEach(index -> executorService.submit(() -> {
                Thread.sleep(10_000);
                System.out.println(String.format("Finished task number: %s", index));
                return null;
            }));
        } finally {
            executorService.shutdown();
        }

        // We are going to check the status of the application after 20 seconds
        executorService.awaitTermination(20, TimeUnit.SECONDS);
        if (executorService.isTerminated()) {
            System.out.println("All scheduled tasks have been finished");
        } else {
            System.out.println("Some tasks haven't finished yet.");
        }
    }
}
