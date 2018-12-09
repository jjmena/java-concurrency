# How can we block a thread until other threads reach a specific state?

`CyclicBarrier` provides us a mechanism to block a set of threads. When all threads reach the barrier, all of them will continue its execution.

First of all we create the barrier with a fixed number of threads:

```java
CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
```

`CyclicBarrir` offer us several methods such as:

* `CyclicBarrier.await()` method makes all threads wait until all threads reach the barrier.
* `CyclicBarrier.reset()` method makes reusable the cyclic barrier again.

In this example we launch 10 threads and wait for a random number of seconds. When all threads reach the barrier, all threads will continue with their execution.
