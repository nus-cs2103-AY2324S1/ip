package task;

public class Deadline extends Task {
    private static final String TASK_HEADER = "[D] ";
    private final String time;

    public Deadline(String name, String time) {
        super(name);
        this.time = time;
    }

    public Deadline(String bool, String name, String time) {
        super(name, Boolean.parseBoolean(bool));
        this.time = time;
    }

    @Override
    public String fileWriteFormatted() {
        return Deadline.TASK_HEADER + Task.UNIQUE_FILE_SEPARATOR +
                super.fileWriteFormatted() + Task.UNIQUE_FILE_SEPARATOR + this.time;
    }

    @Override
    public String toString() {
        return Deadline.TASK_HEADER + super.toString() + "(by: " + this.time + ")";
    }
}
