# How can we get the result from a submitted task?

As we can see in the previus example related with `Future` the `get()` method offer us the result of the task. But, what happen if this task takes long time to finish?

We are able to launch the `get()` method controlling the time we want to wait until the result.

For example, we can specify the time we want to wait (amount of time + unit):

```java
result.get(10, TimeUnit.SECONDS);
```

In case that the result hasn't been reached in the amount of time specify, the `get()` method will raise an exception `TimeoutException`.

In our example we launch a task which sleep a random amount of seconds. We wait for the result only 10 seconds.