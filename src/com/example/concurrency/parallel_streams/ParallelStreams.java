package com.example.concurrency.parallel_streams;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * In this class we can see several examples about using parallel streams.
 */
public class ParallelStreams {

    public static void main(String[] args) {
        List<Integer> data = IntStream.range(1, 1_000_000).boxed().collect(Collectors.toList());

        // First solution we are going to process it in parallel to print them
        System.out.println("[FIRST] Init");
        data.parallelStream().forEach(System.out::println);
        System.out.println("[FIRST] End");

        // Second we are going to sum all data in parallel
        System.out.println("[SECOND] Init");
        long start = System.currentTimeMillis();
        Integer sum = data.parallelStream().reduce(0, (a,b) ->  a + b);
        System.out.println(String.format("Time spend in sum in parallel %s with value %s", System.currentTimeMillis() - start, sum));
        System.out.println("[SECOND] End");

        // Third we are going to sum all data in sequential
        System.out.println("[THIRD] Init");
        start = System.currentTimeMillis();
        sum = data.parallelStream().reduce(0, (a,b) ->  a + b);
        System.out.println(String.format("Time spend in sum in sequential %s with value %s", System.currentTimeMillis() - start, sum));
        System.out.println("[THIRD] End");


    }
}
