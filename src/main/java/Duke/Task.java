package Duke;

public class Task{
    private String task;
    private boolean isDone;

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

    public String toString() {
        if (isDone) {
            return "[X] " + task;
        }
        return "[ ] " + task;

    }
}

