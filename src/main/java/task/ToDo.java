package task;

import java.time.format.DateTimeFormatter;

public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", super.getStatusIcon(), description);
    }

    @Override
    public String toFileFormat(DateTimeFormatter formatter) {
        return String.format("T | %s | %s", super.isDoneString(), description);
    }
}
