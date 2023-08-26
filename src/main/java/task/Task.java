package task;

import java.io.FileWriter;

/**
 * Encapsulates the abstract concept of a Task.
 * @author Donovan Chan Jia Jun
 */
public abstract class Task {
    protected boolean isComplete;
    protected String name;
    public Task(String name) {
        this.isComplete = false;
        this.name = name;
    }

    public Task(String name, boolean isComplete) {
        this.isComplete = isComplete;
        this.name = name;
    }

    /**
     * Check if task is complete.
     * @return boolean. True if completed
     */
    public boolean isComplete() {
        return this.isComplete;
    }

    /**
     * Marks the task to be completed.
     */
    public void markDone() {
        this.isComplete = true;
    }

    /**
     * Marks the task to be incomplete.
     */
    public void markUndone() {
        this.isComplete = false;
    }

    /**
     * Obtains the String representation of whether task is completed.
     * @return String. Symbol of completion
     */
    public String getMarking() {
        if (isComplete()) {
            return "[X]";
        }
        return "[ ]";
    }

    public String getName() {
        return this.name;
    }
    abstract public void writeToFile(FileWriter fileWriter);
}
