package task;

import java.io.Serializable;

public class Task implements Serializable {
    private String name;
    private boolean isDone;

    /**
     * The constructor for the Task class
     *
     * @param name The name of the task
     */
    public Task(String name){
        this.name = name;
        this.isDone = false;
    }

    /**
     * Set whether the task is marked as "done" or not
     *
     * @param isDone Set to "true" if the task is marked as done, "false" otherwise.
     */
    public void setIsDone(boolean isDone){
        this.isDone = isDone;
    }

    /**
     * Returns whether the task is marked as done or not.
     *
     * @return "true" if the task is marked as done and "false" otherwise.
     */
    public boolean getIsDone(){
        return this.isDone;
    }

    /**
     * Get the string representation of the task.
     *
     * @return string representation of the task
     */
    @Override
    public String toString(){
        return "[" + (this.isDone ? "X" : " ") + "] " + this.name;
    }

    /**
     * Get the name of the task.
     *
     * @return task name
     */
    public String getName(){
        return this.name;
    }
}
