public class Event extends Task {
    private String start;
    private String end;

    public Event(String task, String start, String end) throws DukeException{
        super(task);
        if (start.isEmpty() || start.equals(" ")) {
            throw new DukeException("Missing time !!!\n");
        } else if (end.isEmpty() || end.equals(" ")) {
            throw new DukeException("Missing time!!!\n");
        }
        this.start = start;
        this.end = end;
    }

    public Event(String task, String start, String end, boolean finish) throws DukeException{
        super(task, finish);
        if (start.isEmpty() || start.equals(" ")) {
            throw new DukeException("Missing time !!!\n");
        } else if (end.isEmpty() || end.equals(" ")) {
            throw new DukeException("Missing time!!!\n");
        }
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " from: " + this.start + " to: " + this.end;
    }
}
