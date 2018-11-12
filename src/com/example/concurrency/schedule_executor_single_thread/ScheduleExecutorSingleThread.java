package com.example.concurrency.schedule_executor_single_thread;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * The goal of this example is shown the possibility to generate a scheduled executor to launch tasks periodically.
 *
 */
public class ScheduleExecutorSingleThread {

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = null;

        // We are going to schedule some tasks
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        // Fist task every 5 seconds (the first execution will be just on start up because initial delay is 0)
        Runnable firstTask = () -> System.out.println(String.format("FIRST TASK every 5 seconds at %s", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)));
        scheduledExecutorService.scheduleWithFixedDelay(firstTask, 0, 5, TimeUnit.SECONDS);

        // Second task every 20 seconds (the first execution will be just on start up because initial delay is 0)
        Runnable secondTask = () -> System.out.println(String.format("SECOND TASK every 20 seconds at %s", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)));
        scheduledExecutorService.scheduleWithFixedDelay(secondTask, 0, 20, TimeUnit.SECONDS);

        // Await one minute to kill it. In this case we haven't sent a kill signal, then it going to consume one minute
        // and will be forced to finish the execution
        scheduledExecutorService.awaitTermination(1, TimeUnit.MINUTES);
        scheduledExecutorService.shutdownNow();

    }
}
