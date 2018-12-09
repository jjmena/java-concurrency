# How can we block a thread until other threads reach a specific state?

`CountDownLatch` provides us a mechanism to block a thread until other threads reach an specific state.

First of all we define the `CountDownLatch` with a fixed number of threads such as:

```$java
    CountDownLatch countDownLatch = new CountDownLatch(10);
```

Once created we have two different operations:

* `CountDownLatch.await()` method makes a thread to wait until the count down reach desired value.
* `CountDownLatch.countDown()` method decreases the counter. When all threads decrease the counter the await thread will continue.

In the example we launch 10 tasks which wait for a random amount of seconds. When all threads wait the random number of seconds the main thread complete the result continuing its execution. 
