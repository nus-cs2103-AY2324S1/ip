package duke.task;

public class Event extends Task {
    private String start;
    private String end;

    public Event (String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toSave() {
        return (super.isComplete ? "1 " : "0 ") + "event " + super.name + "/from" + start + "/to" + end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + start + "to:" + end + ")";
    }
}
