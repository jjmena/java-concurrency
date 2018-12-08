# How can we await for several tasks execution?

To stop an executor services exists two interesting methods:
* `shutdown()` method mark the executor service as shutting down. It means the executor service are not allowed to add more tasks. In case of submit another task, an error will be raise.
* `shutdownNow()` method attempts to stop all active threads. This is a rude way to stop threads and some of them could leave the system unstable.

When we use `shutdown()` method to stop tasks in executor service, we could await finalization before taking other actions (such as call `shutdownNow`).
To await for executor service shutting down we could use the method `awaitTermination` to wait until executor service finished all task. Furthermore, this way to await for finalization could be limited with a timeout.

In the example of this section we show an executor services which launches 100 tasks with 20 threads. When all tasks have been submitted, we shutdown the executor service and wait for it finalization for 20 seconds.  