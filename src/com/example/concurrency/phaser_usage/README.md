# How can we block a thread until other threads reach a specific state?

`Phaser` provides us a mechanism to block a thread until other threads reach an specific state. In this case we will call this state as phase.

In this case we don't have to define the number of threads at the beginning.

```java
Phaser phaser = new Phaser();
```

When we have defined the phaser we could do two operations:

* `Phaser.arriveAndAwaitAdvance();` method makes us to wait until a specific phase.
* `Phaser.register()` & `Phaser.arriveAndDeregister()` methods allow us to register a new thread in the phaser and deregister it to reach the desired status to unlock the waiting thread.

In this example we launch 10 threads to wait a random number of seconds while main thread is waiting until all threads reach the desired phase. When all threads reach the next phase, the main one will continue.