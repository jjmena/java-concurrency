# What is the difference between each method for thread synchronization?

*CountDownLatch*
* Offer us a way to control a fixed number of threads. This number of threads should be defined in `CountDownLatch` object creation.
* This kind of object can be used only once. Once reach the synchronization state we cannot reuse it again.
* Offer us to kind of operations: wait for synchronization(`CountDownLatch.await()`) or continue execution decreasing the counter (`CountDownLatch.countDown()`).


*CyclicBarrier*
* Offer us a way to control a fixed number of threads. This number of threads should be defined in `CyclicBarrier` object creation.
* This kind of object can be reused. We can use `CyclicBarrier.reset()` method to restart it.
* Only exists one way to work, it means is only wait. When all thread reach the barrier, it will be open and all threads will continue its execution. 

*Phaser*
* Offer us a way to control a NOT fixed number of threads. Each thread will be added to the Phaser using `Phaser.register()` method.
* As `CyclicBarrier` this kind of object can be reusable.
* Offer us to ways of working. The first one wait unit reach a spefic state with `Phaser.arriveAndAwaitAdvance()`. The second way of working is continue execution and modifying the state with `Phaser.arrive()`.
* This kind of mechanism allows several phases, it means that several threads could we stopped awaiting for different phases.
