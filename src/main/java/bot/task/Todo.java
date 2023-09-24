package bot.task;

import java.time.LocalDateTime;

public class Todo extends Task {
    private static final String TASK_HEADER = "[T] ";
    public Todo(String name) {
        super(name);
    }

    /**
     * Creates an instance of Todo object
     *
     * @param bool the boolean for whether task is completed
     * @param name the Task description
     */
    public Todo(String bool, String name) {
        super(name, Boolean.parseBoolean(bool));
    }

    /**
     * Returns a String representation of a Todo object formatted to be stored into data/task.txt
     *
     * @return a String formatted for writing into data/task.txt
     */
    @Override
    public String fileWriteFormatted() {
        return Todo.TASK_HEADER + Task.UNIQUE_FILE_SEPARATOR + super.fileWriteFormatted();
    }

    /**
     * Returns false as todo tasks have no deadline
     *
     * @param dateTime The current date and time to compare against.
     * @return false
     */
    @Override
    public boolean isOverdue(LocalDateTime dateTime) {
        return false;
    }

    /**
     * Returns a string representation of the Todo object.
     *
     * @return a string representation of the Todo object.
     */
    @Override
    public String toString() {
        return Todo.TASK_HEADER + super.toString();
    }
}
