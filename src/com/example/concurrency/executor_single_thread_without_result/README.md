# How can we create Threads?
We can launch threads using three different ways:

* Creating a class extending `Thread` class
    * This is the basic solution used by many people
    * This solution has a problem in case of a class that extends from another class.
* Implements interface `Runnable`.
    * This interface doesn't allow return values or throw checked exceptions
* Implements interface `Callable`
    * This interface allows return values or throw checked exceptions. 

In the example inside this folder shows the way use `Runnable` interface to create Threads using SingleThreadExecutor to launch them without paralelism (one thread to launch each `Runnable).

In this case the purpose is show the way to reuse the same thread to launch different tasks.

In this case we use `ExecutorService.execute` method to launch the tasks. With this method we doesn't expect any result. 
