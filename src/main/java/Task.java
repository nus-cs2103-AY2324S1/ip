enum TaskType {
    TODO, DEADLIINE, EVENT;
}

public abstract class Task {
    String taskName;
    boolean isDone = false;
    TaskType taskType;
    //constructor to create a Task object with a task description
    public Task(String taskName, TaskType taskType) {
        this.taskName = taskName;
        this.taskType = taskType;
    }
    //to return a String with Task name and whether it is marked done
    public String toString(){
        if (isDone) {
            return "[X] " + taskName;
        } else {
            return "[ ] " + taskName;
        }
    };

    // mark task as done, by changing value of isDone to true
    public abstract void markDone();

    // mark task as undone, by changing value of isDone to false
    public abstract void unmarkDone();
}
