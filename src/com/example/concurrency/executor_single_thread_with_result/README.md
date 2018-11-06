# How can we launch tasks and receive results?
In case that we want to receive a result from a task launched in a Thread we should use the next interface:
* Future<>
    * This interface allows us to wait until execution finished
    * Once this execution is finished we can ask about the result
    * When we launch a `Runnable` task, we won't have a result (null value)
    
In case we want a result, we should use `ExecutorService.submit` instead of `ExecutorService.execute`. 
