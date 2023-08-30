package Duke.Tasks;

import java.io.Serializable;

public class Task implements Serializable {
    protected String description;
    protected boolean isDone;
    protected Character taskType;

    // tasks have a tasktype field which should be edited based on the task type. otherwise, remains as '_'
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType='_';
    }

    public Task(String description, Character taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    //getters and setters for the fields
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public void setNotDone() {
        isDone = false;
    }

    public void setDone() {
        isDone = true;
    }

    public Character getTaskType() {
        return this.taskType;
    }

    @Override
    public String toString() {
        return this.getDescription();
    }
}