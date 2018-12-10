package com.example.concurrency.basic_synchronization;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BasicSynchronization {

    private static int counterWithoutMonitor = 0;

    private static int counterWithMonitor = 0;

    private void incrementAndPrintNoMonitor() throws InterruptedException {
        System.out.print(++counterWithoutMonitor + " ");
    }

    private synchronized void incrementAndPrintMonitor() throws InterruptedException {
        System.out.print(++counterWithMonitor + " ");
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        BasicSynchronization basicSynchronization = new BasicSynchronization();

        System.out.println("Example without monitor running");

        Callable<Object> callableWithoutMonitor = () -> {
            basicSynchronization.incrementAndPrintNoMonitor();
            return null;
        };

        List<Callable<Object>> noMonitorCallables = IntStream.range(0, 10)
                .mapToObj(index -> callableWithoutMonitor)
                .collect(Collectors.toList());

        executorService.invokeAll(noMonitorCallables);

        System.out.println(String.format("\nExample without monitor ended with counter value %s", counterWithoutMonitor));


        System.out.println("Example with monitor running");

        Callable<Object> callableWithMonitor = () -> {
            basicSynchronization.incrementAndPrintMonitor();
            return null;
        };

        List<Callable<Object>> monitorCallables = IntStream.range(0, 10)
                .mapToObj(index -> callableWithMonitor)
                .collect(Collectors.toList());

        executorService.invokeAll(monitorCallables);

        System.out.println(String.format("\nExample with monitor ended with counter value %s", counterWithMonitor));

        executorService.shutdown();

    }
}
