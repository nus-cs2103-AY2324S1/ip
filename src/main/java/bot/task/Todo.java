package bot.task;

public class Todo extends Task {
    private static final String TASK_HEADER = "[T] ";
    public Todo(String name) {
        super(name);
    }

    public Todo(String bool, String name) {
        super(name, Boolean.parseBoolean(bool));
    }

    @Override
    public String fileWriteFormatted() {
        return Todo.TASK_HEADER + Task.UNIQUE_FILE_SEPARATOR + super.fileWriteFormatted();
    }

    @Override
    public String toString() {
        return Todo.TASK_HEADER + super.toString();
    }
}
