# How can we use the default thread pool generated in Java?

Java provides us by default a Thread Pool called ForkJoinPool pool. As we saw in the example for parallel streams, is the one used to launch parallel streams.

We can take advantage of this pool, but the size of this pool is limited by this formula: nº CPUs - 1.

Furthermore, Java offers us a framework 

