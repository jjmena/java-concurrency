package com.example.concurrency.fork_join_usage;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * In this example we show how to calculate Fibonacci number using Fork/Join framework.
 */
public class ForkJoinExample {

    public static void main(String[] args) {
        FiboTask fibonacci = new FiboTask(40);
        ForkJoinPool pool = new ForkJoinPool();
        int result = pool.invoke(fibonacci);
        System.out.println(result);
    }

    /**
     * RecursiveTask created for Fork/Join Framework. This kind of class return a value
     */
    public static class FiboTask extends RecursiveTask<Integer> {

        final int number;

        FiboTask(int number) {
            this.number = number;
        }

        @Override
        protected Integer compute() {
            if (number <= 1) {
                return number;
            }
            FiboTask task1 = new FiboTask(number - 1);
            task1.fork();
            FiboTask task2 = new FiboTask(number - 2);
            return task2.compute() + task1.join();
        }
    }
}
