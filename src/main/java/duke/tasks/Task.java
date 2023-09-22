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
    private boolean isDone;

    /**
     * Constructs a Task object with the specified description.
     *
     * @param description The task's description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task object with the specified description and completion status.
     *
     * @param description The task's description.
     * @param isDone Whether the task is completed.
     */
    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks or unmarks the task as done.
     * Marks the task as done if the specified boolean is true, and unmarks the task as done if otherwise.
     *
     * @param isDone Whether the task has been completed.
     */
    public void markTaskCompleted(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        String checkbox = "";
        if (this.isDone) {
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
        if (isDone) {
            return "1|" + this.description;
        } else {
            return "0|" + this.description;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Task) {
            boolean hasSameDescription = ((Task) obj).description.equals(this.description);
            boolean hasSameStatus = ((Task) obj).isDone == this.isDone;
            return hasSameDescription && hasSameStatus;
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

    /**
     * Compares the Task's description with that of the specified task lexicographically.
     * Returns an integer that is negative, zero, or positive if the Task's description is
     * less than, equal to, or greater than that of the other task respectively.
     *
     * @param otherTask The other Task to be compared with.
     * @return An integer representing the comparison.
     */
    public int compareDescription(Task otherTask) {
        return this.description.compareTo(otherTask.description);
    }

    /**
     * Compares the Task's date with that of the specified task chronologically.
     * This method compares using the task deadline for DeadlineTasks and
     * the starting time for EventTasks. Tasks without specified dates (e.g. ToDoTasks)
     * will always be considered greater than those with dates.
     * Returns an integer that is negative, zero, or positive if the Task's date is
     * less than, equal to, or greater than that of the other task respectively.
     *
     * @param otherTask The other Task to be compared with.
     * @return An integer representing the comparison.
     */
    public abstract int compareDate(Task otherTask);

    /**
     * Compares the Task's type with that of the specified task.
     * Task types are compared in the following order:
     * ToDoTask < DeadlineTask < EventTask.
     * Returns an integer that is negative, zero, or positive if the Task's type is
     * less than, equal to, or greater than that of the other task respectively.
     *
     * @param otherTask The other Task to be compared with.
     * @return An integer representing the comparison.
     */
    public abstract int compareType(Task otherTask);
}
