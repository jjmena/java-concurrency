# How to avoid several threads reach a critical section at the same time?

There is an alternative way to avoid reaching a critical section. In this case we could use `Lock` object to control acccess to a critical section.

`Lock` interface define several types of locks depending the necessity of the application:

* `ReadLock`  
* `WriteLock`
* `ReentrantLock`

We could combine those locks to be able to access to a critical section with different purposes combining read and write locks.

The advantages of this way of defining locks are:

* Provides a way to try to acquire lock rather than wait.
* Provides us the possibility to interrupt locking.
* Be able to define explicitly lock/unlock operations to define the critical section.
* Be able to define two kind of usages for the lock: ReadLock, WriteLock, ReentrantLock.

In this example we show the way to lock and unlock a critical section using `Lock` interface.
 