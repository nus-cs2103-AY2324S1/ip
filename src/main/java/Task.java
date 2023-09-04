import java.util.ArrayList;

public class Task {
    protected String description;
    protected boolean isDone;
    private static String LINE = "-----------------------------------------\n";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static Task parse(String input) {
        if (input.startsWith("todo ")) {
            return Todo.todoParse(input.substring(5));

        } else if (input.startsWith("deadline ")) {
            // empty deadline
            // no by
            return Deadline.deadlineParse(input.substring(9));

        } else if (input.startsWith("event ")) {
            // empty event
            // no from
            // no to
            return Event.eventParse(input.substring(6));
        } else {
            return null;
        }
    }

    public static Task savedParse(String input) {
        if (input.startsWith("[T]")) {
            // split using | and read everything including mark
            return Todo.todoParse(input.substring(7));

        } else if (input.startsWith("[D]")) {
            // empty deadline
            // no by
            return Deadline.deadlineSavedParse(input.substring(7));

        } else if (input.startsWith("[E]")) {
            // empty event
            // no from
            // no to
            return Event.eventSavedParse(input.substring(7));
        } else {
            return null;
        }
    }
    public String getStatus() {
        return (isDone ? "X" : " ");
    }
    public void setStatus(boolean bool) {
        isDone = bool;
    }
    public String toString() {
        return "[" + getStatus() + "] " + description;
    }
}
