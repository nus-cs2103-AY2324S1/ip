import exception.DukeException;

public class Event extends Task {
    private String from;
    private String to;

    public Event(String task, String from, String to) throws DukeException {
        super(task);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getStatus(){
        String time = "(from: " + from + " to: " + to + ")";
        return "[Event]" + super.getStatus() + " " + time;
    }

    @Override
    public String getTime() {
        return this.from + " " + this.to;
    }

    @Override
    public String toFileString() {
        return super.isDone ? ("E | 1 | " + super.task + " | " + this.from + "-" + this.to)
        : ("E | 0 | " + super.task + " | " + this.from + "-" + this.to);
    }
}