# How can we deal with multi-threads?
Since Java 5, the new concurrency API is based on `ExecutorService` interface to launch Threads and the `Executors` factory to generate each `ExecutorService`.
This factory offer us the possibility to generate these `ExecutorService`:
* `newSingleThreadExecutor` creates a single thread executor. With this kind of executor we have only one thread to launch every task.
* `newCachedThreadPool` creates a new thread when it's needed. We we have idle threads, they will be reused when it's needed.
* `newFixedThreaPool` creates a thread pool which reuses them. It's created with a fixed number of threads. The number of threads is passed as parameter.

Futhermore, it exists another interface called `ScheduledExecutorService` to launch scheduled task (for example after a delay or periodically)
* `newSingleThreadScheduledExecutor` creates a single thread executor for scheduled tasks. This executor allow us to schedule task. In case of several tasks are scheduled at the same time, they will be executed in different time because we have only one thread. Therefore one of them won't fit the delay value (it will be bigger).
* `newScheduledThreadPool` creates a multi-threaded executor for scheduled tasks. The number of available threads is passed as parameter.

In this example we show tasks running at the same time, because we defined a multithread executor (2 threads). We will see during first task execution messages from the second task. 
