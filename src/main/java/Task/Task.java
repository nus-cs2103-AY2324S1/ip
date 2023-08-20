package Task;

public class Task {
    private boolean isDone = false;

    private String taskName = "";

    private Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    public static Task newTask(String taskName) {
        return new Task(taskName, false);
    }


    public void markAsDone(){
        this.isDone = true  ;
    }

    public void markAsUndone(){
        this.isDone = false;
    }
    /**
     * Returns the string representation of Task
     * @return the string representation of task
     */
    @Override
    public String toString(){
        String statusIcon = "";
        if (this.isDone) {
            statusIcon = "X";
        } else if (!this.isDone) {
            statusIcon = " ";
        }
        return String.format("[%s] %s", statusIcon, this.taskName);
        // return in format [statusIcon] taskname
    }

}
