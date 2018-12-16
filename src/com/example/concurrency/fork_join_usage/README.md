# How can we use the default thread pool generated in Java?

Java provides us by default a Thread Pool called ForkJoinPool pool. As we saw in the example for parallel streams, is the one used to launch parallel streams.

We can take advantage of this pool, but the size of this pool is limited by this formula: nº CPUs - 1.

Furthermore, Java offers us a framework to divide a task in several subtasks which could be launched separately and after that the result of all tasks could be joined to get the final result.

First of all, this framework offer us the interface `ForkJoinTask`. This interface offers us two main abstract classes 

* `RecursiveAction` offers us the possibility to launch a computation without a result returned.
* `RecursiveTask<T>` offers us the possibility to launch a compuation giving us a result of the operation.

For those classes we have to implement the method `compute()` to start the process

Apart from those abstract classes implementation, that help us to reduce only the scope of the code to fill (only `compute()` method), they offer us other methods to be considered:

* `fork()` method launches asynchronously the operation. 
* `join()` method waits until the operation finishes to get the result of it.

In our example we launched a Fibonacci example based on Fork/Join Framework. Furthermore we have the calculation of Fibonacci number using an iterative way showing us the difference of time for this calculation.
The difference of execution time is:

```
Result iterative for 46 fibonnaci number: 1836311903 in 1 ms 
Result Fork/Join for 46 fibonnaci number: 1836311903 in 44840 ms
```

In this case iterative solution is much better than Fork/Join, but it depends of the number of threads in the pool. If we increase the number of threads the result could be quite similar.

