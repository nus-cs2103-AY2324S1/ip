public class Event extends Task {
    Event(String task) {
        super(task);
    }

    Event(String task, boolean isDone) {
        super(task, isDone);
    }
    @Override
    public Event done() {
        return new Event(super.getTask(), true);
    }
    @Override
    public Event undone() {
        return new Event(super.getTask(), false);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
