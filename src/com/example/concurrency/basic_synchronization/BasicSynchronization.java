package com.example.concurrency.basic_synchronization;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BasicSynchronization {

    private int counterNoSync = 0;

    private void incrementAndPrintNoSync() throws InterruptedException {
        System.out.print(++counterNoSync + " ");
        Thread.sleep(1_000);
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        BasicSynchronization basicSynchronization = new BasicSynchronization();

        System.out.println("Not Synchronized");

        Callable<Object> noAsyncCallable = () -> {
            basicSynchronization.incrementAndPrintNoSync();
            return null;
        };


        // Execution as async
        /*
        List<Callable<Object>> noSyncCallables = IntStream.range(0, 1001)
                .mapToObj(index -> noAsyncCallable)
                .collect(Collectors.toList());
        List<Future<Object>> futures = executorService.invokeAll(noSyncCallables);
        */
        IntStream.range(1, 11).forEach(index -> executorService.submit(noAsyncCallable));

        System.out.println("");
        System.out.println("Synchronized");

        executorService.shutdown();

        // TODO: JJMENA: Complete it!!

    }
}
