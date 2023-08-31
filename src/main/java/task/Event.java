package task;

public class Event extends Task {

    private final String start;
    private final String end;

    public Event(boolean completed, String nameOfTask, String start, String end) {
        super(completed, nameOfTask);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        if (completed) {
            return "[E][X] " + nameOfTask + " (from: " + start + " to: " + end + ")";
        } else {
            return "[E][ ] " + nameOfTask + " (from: " + start + " to: " + end + ")";
        }
    }

    @Override
    public String typeOfString() {
        return "E ";
    }

    @Override
    public String taskDetailsString() {
        return super.nameOfTask + " |" + " " + start + " |" + " " + end;
    }
}
