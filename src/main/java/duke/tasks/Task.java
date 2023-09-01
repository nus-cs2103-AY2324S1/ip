package duke.tasks;

/**
 * Encapsulates a task to be stored in the task list of the Duke chatbot.
 * This is an abstract class meant to be inherited by concrete subclasses,
 * e.g. ToDoTask, DeadlineTask, and EventTask.
 *
 * @author Wu Jingya
 */
public abstract class Task {
    private String description;
    private boolean done;

    /**
     * Constructs a Task object with the specified description.
     *
     * @param description The task's description.
     */
    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    /**
     * Constructs a Task object with the specified description and completion status.
     *
     * @param description The task's description.
     * @param done Whether the task is completed.
     */
    public Task(String description, Boolean done) {
        this.description = description;
        this.done = done;
    }

    /**
     * Marks or unmarks the task as done.
     * Marks the task as done if the specified boolean is true, and unmarks the task as done if otherwise.
     *
     * @param done Whether the task has been completed.
     */
    public void markTaskCompleted(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        String checkbox = "";
        if (this.done) {
            checkbox = "[X]";
        } else {
            checkbox = "[ ]";
        }
        return checkbox + " " + this.description;
    }

    /**
     * Returns the string representation of the task's data to be saved into a text file.
     * This formatting is consistent and understood by the chatbot's Storage component in charge
     * of reading and writing all the tasks' data to hard drive.
     *
     * @return The string representation of the task's data.
     */
    public String toData() {
        if (done) {
            return "1|" + this.description;
        } else {
            return "0|" + this.description;
        }
    }

    //For testing purposes only
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Task) {
            return ((Task) obj).description.equals(this.description) && ((Task) obj).done == this.done;
        }
        return false;
    }

    /**
     * Checks whether the Task's description contains the specified keyword.
     *
     * @param keyword The keyword.
     * @return Whether the Task's description contains the keyword.
     */
    public boolean containsWord(String keyword) {
        String[] words = this.description.split(" ");
        for (String word : words) {
            if (word.equalsIgnoreCase(keyword)) {
                return true;
            }
        }
        return false;
    }
}
