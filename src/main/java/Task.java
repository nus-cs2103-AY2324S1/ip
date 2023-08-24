public abstract class Task {
    String taskName;
    boolean isDone = false;
    //constructor to create a Task object with a task description
    public Task(String taskName) {
        this.taskName = taskName;
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
