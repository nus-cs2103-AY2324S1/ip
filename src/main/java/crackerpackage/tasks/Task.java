package crackerpackage.tasks;

import Exceptions.EmptyDescriptionException;

/**
 * An abstract class that implements the basic functions of a Task
 *
 * @author Anton Tan Hong Zhi
 */

abstract public class Task{
    private boolean isDone;
    private String description;

    /**
     * Creates a Task object, called by subclasses.
     *
     * @param s description of the Task
     * @throws EmptyDescriptionException
     */
    Task(String s) throws EmptyDescriptionException {
        if(s.isBlank()){
            throw new EmptyDescriptionException();
        }
        this.description = s;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markDone(){
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markUndone(){
        this.isDone = false;
    }

    /**
     * Returns the description of the Task
     *
     * @return A string representing the description of the task.
     */
    public String getDesc(){
        return description;
    }

    /**
     * Returns the status of the Task.
     *
     * @return whether the task is done or not
     */
    public boolean getStatus(){
        return isDone;
    }

    /**
     * Returns the string representation of the Task.
     *
     * @return a string representing the Task
     */
    public String toString(){
        return "[" + (isDone?"X":" ") + "] " + description;
    }
}