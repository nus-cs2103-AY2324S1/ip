package Duke;

/**
 * Class to encapsulate the features of a task.
 */
public class Task{
    private String task;
    private boolean isDone;

    /**
     * public constructor for Task.
     * @param task
     */
    public Task(String task){
        this.task = task;
        isDone=false;
    }

    public void setDone() {
        isDone=true;
    }
    public void setUndone(){
        isDone=false;
    }
    public boolean getDone() {
        return isDone;
    }

    public boolean contains (String val) {
        return task.contains(val);
    }

    public String toString(){
        if(isDone)
            return "[X] "+ task;
        return "[ ] "+ task;

    }
}

