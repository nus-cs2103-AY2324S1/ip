public class Event extends Task {
    private String from;
    private String to;
    public Event(String name, String from, String to) throws DukeException {
        super(name);
        if (name.trim().isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a Task cannot be empty.");
        }
        this.from = from;
        this.to = to;
    }

    public String dataString() {
        if (this.isdone()) {
            return "T : 1 : " + this.getname() + ":" + this.from + ":" + this.to;
        } else {
            return "T : 0 : " + this.getname() + ":" + this.from + ":" + this.to;
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + ") (to: " + this.to + ")";
    }
}
