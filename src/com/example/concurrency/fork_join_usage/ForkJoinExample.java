package com.example.concurrency.fork_join_usage;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * In this example we show how to calculate Fibonacci number using Fork/Join framework.
 */
public class ForkJoinExample {

    public static final int FIBO_NUMBER = 46;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        FiboIt fiboIt = new FiboIt();
        long resultIt = fiboIt.compute(FIBO_NUMBER);
        System.out.println(String.format("Result iterative for %s fibonnaci number: %s in %s ms ", FIBO_NUMBER, resultIt, System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        FiboTask fiboTask = new FiboTask(FIBO_NUMBER);
        ForkJoinPool pool = new ForkJoinPool();
        long resultForkJoin = pool.invoke(fiboTask);
        System.out.println(String.format("Result Fork/Join for %s fibonnaci number: %s in %s ms", FIBO_NUMBER, resultForkJoin, System.currentTimeMillis() - start));
    }

    /**
     * RecursiveTask created for Fork/Join Framework. This kind of class return a value
     */
    public static class FiboTask extends RecursiveTask<Long> {

        final long number;

        FiboTask(long number) {
            this.number = number;
        }

        @Override
        protected Long compute() {
            if (number <= 1) {
                return number;
            }
            FiboTask task1 = new FiboTask(number - 1);
            task1.fork();
            FiboTask task2 = new FiboTask(number - 2);
            return task2.compute() + task1.join();
        }
    }

    public static class FiboIt {

        public long compute(long n) {
            if (n <= 1) {
                return n;
            }
            int fib = 1;
            int prevFib = 1;

            for (int i = 2; i < n; i++) {
                int temp = fib;
                fib += prevFib;
                prevFib = temp;
            }
            return fib;
        }

    }


}
