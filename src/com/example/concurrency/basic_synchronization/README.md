# How to avoid several threads reach a critical section at the same time?

Java offer us a basic wait to control that several threads doesn't reach a critical section at the same time. The mechanism that Java offer us by default is called monitor.

To do it we should use `synchronized` keyword to define critical section.

There are several ways to define critical section with `synchronized` keyword.

*Synchronized method (instance)*

```$java
public synchronized void criticalSectionMethod() {
    counter = counter + 1;
}
```

In this case we use the current instance to define the monitor

*Synchronized method (class)*

```$java
public static synchronized void criticalSectionMethod() {
    counter = counter + 100;
}
```

In this case we use the class itself as monitor.

*Synchronized block*

```$java
public void method() {
    synchronized(this) {
        counter = counter + 1;
    }
}
```

In this case we are using current object to define the monitor. It is not mandatory to use the current object, we can use another object, even a class, to use as monitor.

```$java
public static void method(){
    synchronized (MyClass.class) {
        counter = counter + 100;
    }
}
```

In this example we have defined two cases of a counter. The first example without monitor, and the second one with monitor. We would have this result (it is going to depend on the threads execution):

```
Example without monitor running
1 1 2 3 4 5 6 7 8 9 
Example without monitor ended with counter value 9
Example with monitor running
1 2 3 4 5 6 7 8 9 10 
Example with monitor ended with counter value 10
```

In this example we can see that we updated the value 1 twice in a wrong way.
