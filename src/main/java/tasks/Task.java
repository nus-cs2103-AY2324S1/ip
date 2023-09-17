package tasks;

public class Task {
    private String name;
    public boolean isComplete;

    public Task(String name) {
        this.name = name;
        this.isComplete = false;
    }

    /**
     * Returns a string that represents the completion status
     * of a task
     *
     * @return "X" if task completed and an empty string if not completed
     */
    public String getMark() {
        return (isComplete ? "X" : " ");
    }


    /**
     * Returns a string that represents the Task
     *
     * @return string with details of the Task
     */
    public String toString() {
        return "[" + getMark() + "] " + name;
    }


    public void markTask() {
        this.isComplete = true;
    }

    public void unmarkTask() {
        this.isComplete = false;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Returns a string that represents the Task to be stored in txt file
     *
     * @return a formatted string with details of the Task
     */
    public String taskToStringStore(Task task) {
        String isCompleteString = (getMark() == "X") ? "X" : "O";
        return isCompleteString + "-" + task.name + "-";
    }

}
