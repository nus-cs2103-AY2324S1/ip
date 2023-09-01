package task;

import parsers.DatetimeParser;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private static final String TASK_HEADER = "[D] ";
    private final LocalDateTime time;

    public Deadline(String name, String time) {
        super(name);
        this.time = DatetimeParser.parseTimeInput(time);
    }

    public Deadline(String bool, String name, String time) {
        super(name, Boolean.parseBoolean(bool));
        this.time = DatetimeParser.convertToLocalDateTime(time);
    }

    @Override
    public String fileWriteFormatted() {
        return Deadline.TASK_HEADER + Task.UNIQUE_FILE_SEPARATOR +
                super.fileWriteFormatted() + Task.UNIQUE_FILE_SEPARATOR + this.time;
    }

    @Override
    public String toString() {
        return Deadline.TASK_HEADER + super.toString() + "(by: " +
                DatetimeParser.reformatTimeOutput(this.time) + ")";
    }
}
