enum TaskType {
    TODO, DEADLINE, EVENT
}

public abstract class Task {
    private String taskName;
    private boolean isDone = false;
    private TaskType taskType;

    //constructor to create a Task object with a task description
    public Task(String taskName, TaskType taskType) {
        this.taskName = taskName;
        this.taskType = taskType;
    }


    /**
     * Return a String with Task description and whether it is marked done
     *
     * @return a String with Task description and whether it is marked done
     */
    public String toString() {
        if (isDone) {
            return "[X] " + taskName;
        } else {
            return "[ ] " + taskName;
        }
    }

    /**
     *  mark task as done, by changing value of isDone to true
      */

    public void markDone() {
        this.isDone = true;
    }

    /**
     *  mark task as undone, by changing value of isDone to false
     */
    public void unmarkDone() {
        this.isDone = false;
    }
}
