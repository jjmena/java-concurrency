# How can we define a variable accessible in read/write mode without synchronized block?
We can use volatile keyword to help us to ensure the visibility of data changes in the variable.

Due to that fact we can see the new updated value. 

When we have multiple thread execution we can use `volatile` keyword. With this keyword we indicate that this variable should be always stored in main memory for all operations (read/write). Due to that fact we are able to increment the value because real information is stored all the time in memory.

In this example we launch the same operation with and without the `volatile` keyword. In this example this solution could we useful rather than use a synchronized section to control the access to a variable in read/write mode. 