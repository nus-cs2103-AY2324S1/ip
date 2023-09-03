package duke;

public class Event extends Task {

    Event(boolean done, String name) {
        super(done, name);
    }

    Event(String name) {
        super(name);
    }

    @Override
    public String taskType() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E] " + super.toString();
    }
}
