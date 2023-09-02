package task;

import java.io.FileWriter;

/**
 * Encapsulates the abstract concept of a Task.
 * @author Donovan Chan Jia Jun
 */
public abstract class Task {
    protected boolean isComplete;
    protected String name;

    /**
     * Constructs the Task object.
     *
     * @param name String representation for the name of the Task
     */
    protected Task(String name) {
        this.isComplete = false;
        this.name = name;
    }

    /**
     * Constructs the Task object.
     *
     * @param name String represetnationf or the name of the Task
     * @param isComplete Boolean value representing if the Task has been marked as complete
     */
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

    /**
     * Returns the name of the Task Object.
     *
     * @return String representation of its name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Checks if given word String is a substring of its name.
     *
     * @param word String repreentingt the name substring they are looking for
     * @return {@code true} if word is a substring of the name
     */
    public Boolean containName(String word) {
        return this.name.contains(word);
    }

    abstract public void writeToFile(FileWriter fileWriter);
}
