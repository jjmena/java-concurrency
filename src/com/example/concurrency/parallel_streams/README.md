# Paralle Streams

Another way to do concurrent programming is using parallel streams. Using parallel streams can improve our application performance without handling manually threads.

By default, the number of threads to proccess parallel streams depends on the number of cores of your CPU.

The way to create a parallel stream is using `parallelStream()` rather than `stream()`.

