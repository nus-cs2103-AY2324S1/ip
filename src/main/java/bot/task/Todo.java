package bot.task;

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
     * Returns a string representation of the Todo object.
     *
     * @return a string representation of the Todo object.
     */
    @Override
    public String toString() {
        return Todo.TASK_HEADER + super.toString();
    }
}
