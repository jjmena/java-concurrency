# Paralle Streams

Another way to do concurrent programming is using parallel streams. Using parallel streams can improve our application performance without handling manually threads.

By default, the number of threads to proccess parallel streams depends on the number of cores of your CPU.

The way to create a parallel stream is using `parallelStream()` rather than `stream()`.

By default `parallelStream()` method uses the default `ForkJoinPool` created as system level. This pool is created by default with this number of threads: Nº CPUs - 1. It means with a 4 cores CPU, ForkJoinPool will be created by default with 3 threads.

In this example we launch three operations with parallel streams:
* Process a list of Integer and print them
* Sum all elements in a list of Integer in parallel and show the total time consumed.
* Sum all elements in a list of Integer in sequential and show the total time consumed. 

