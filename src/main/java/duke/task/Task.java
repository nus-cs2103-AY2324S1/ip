package duke.task;

import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean isDone;
    protected static final String DATETIME_INPUT_FORMAT = "yyyy-MM-dd HHmm";
    protected static final DateTimeFormatter dateTimeInputFormatter
            = DateTimeFormatter.ofPattern(DATETIME_INPUT_FORMAT);

    public Task(String description) {
        this.description = description;
        this.isDone =  false;
    }
    public static Task readFromFile(String line) {
        String[] components = line.split("\\|");
        switch(components[0]) {
            case("T"):
                return Todo.readFromFile(components);
            case("D"):
                return Deadline.readFromFile(components);
            case("E"):
                return Event.readFromFile(components);
            default:
                return null;
        }
    }
    public String writeFileFormat() {
        return (this.isDone ? "1" : "0")  + "|" + this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }
    @Override
    public String toString() {
        return("["
                + this.getStatusIcon()
                + "] "
                + this.description);
    }
}
