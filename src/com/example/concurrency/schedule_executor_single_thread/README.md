# How to schedule periodic or delayed tasks?

We could deal with delayed or periodic tasks using `ScheduledExecutorService`. This service offer us:
* `schedule()` method which will schedule a task after a delay period. This method could be called with a `Callable` or a `Runnable`. In both cases will return a `ScheduleFuture` to collect the result of the task.
* `scheduleAtFixedRate()` method which schedules a new task after a period of time. In this case next task will be scheduled considering initial time of the current task. It means that next task could be launched during the execution of the previous one (*OVERLAP ALLOWED*).
* `scheduleAtFixedDelay()` method which schedules a new task after a period of time. In this case next task will be schedules considering end time of the current task. It means that next task couldn't be launched during the execution of the previous one (*OVERLAP NOT ALLOWED*)

In this example we launch two tasks with different periods (5 and 20 seconds). We schedule them and await for 1 minute to stop the schedule executor service in a rude way using `shutdownNow()` method.