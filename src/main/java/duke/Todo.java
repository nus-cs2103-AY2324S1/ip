package duke;

/**
 * The `Todo` class represents a to-do task, which is a basic type of task with a description.
 * It inherits from the `Task` class and provides specific implementations for to-do tasks.
 */
public class Todo extends Task {

    /**
     * Constructs a new `Todo` task with the given description and save status.
     *
     * @param task        The description of the to-do task.
     * @param isNotSaved  A boolean indicating whether the task needs to be saved.
     */
    public Todo(String task, boolean isNotSaved) {
        super(task, isNotSaved);
        if (isNotSaved) {
            saveToFile();
        }
    }

    /**
     * Returns a formatted string representation of the to-do task.
     *
     * @return A string representing the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Prints a message indicating that the to-do task has been added.
     */
    public void print() {
        System.out.println(Ui.horizontalLine + "Got it. I've added this task:\n " + this.toString()+ "\n"
        + "Now you have " + Task.getCounter() + " tasks in the list\n" + Ui.horizontalLine);
        System.out.println(Ui.horizontalLine + "Got it. I've added this task:\n "
                + this.toString()+ "\n" + "Now you have "
                + Task.getCounter() + " tasks in the list\n" + Ui.horizontalLine);
    }

    /**
     * Generates a string representation of the to-do task for saving to a file.
     *
     * @return A string representation of the to-do task for file storage.
     */
    public String generateStr() {
        return "T | " + (this.getStatus() == TaskStatus.DONE ? 1 : 0)
                + " | " + this.getTask();
    }

    /**
     * Saves the to-do task to a file.
     */
    @Override
    public void saveToFile() {
        Storage.saveTaskToFile(generateStr());
    }

}
