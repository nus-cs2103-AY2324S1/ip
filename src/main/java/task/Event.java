package task;

import task.Task;

public class Event extends Task {

    private String start;
    private String end;

    public Event(String nameOfTask, String start, String end) {
        super(nameOfTask);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        if (completed) {
            return "[E][X] " + nameOfTask + "(from: " + start + "to: " + end + ")";
        } else {
            return "[E][ ] " + nameOfTask + "(from: " + start + "to: " + end + ")";
        }
    }
}
