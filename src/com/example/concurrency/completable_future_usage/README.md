# How can we get the result from a submitted task?

Another way to deal with asynchronous tasks, synchronize them or apply changes after a task finished, could be done using `CompletableFuture` interface.

`CompletableFuture` offers us:

* `CompletableFuture.supplyAsync()` method allows us to launch a asynchronous task.
* `CompletableFuture.thenApply()` method allows to process the result of a asynchronous task.
* `CompletableFuture.thenCombine()` method allows to launch a second task asynchronous task. When both tasks have been finished the result could be combined.
* `CompletableFuture.allOf()` method wait until all completable futures have been finished.
* `CompletableFuture.anyOf()` method wait until one completable futures have been finished.

In our example we have two cases:

1. An example launching 10 threads at the same time which generates a random number after waiting a random number of seconds. When all tasks have been completed we collect the result in a list of results.
2. An example launching 1 thread, and when the task has been completed we apply a function to transform the result obtained in the asynchronous task.