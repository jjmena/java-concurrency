# How can we get the result from a submitted task?

When we launch an asynchronous task we want to get the result of this task. To do that, we could use the `Future` object which contains the result and the state of the task.
`Future` offers use these methods:
* `Future.isDone()` method to check the status of the task. 
* `Future.get()` method returns the result of the task. In case of this task hasn't been finished we will be stopped until the task finalization.

In our example we launch a task which collect a list of integers and result it. We get the information returned from the task obtained from the future created.
