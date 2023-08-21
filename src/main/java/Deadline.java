public class Deadline extends Task{
    private final String due;

    Deadline(String description, String due) {
        super(description);
        this.due = due;
    }

    Deadline(String description, String due, Boolean marked) {
        super(description, marked);
        this.due = due;
    }

    @Override
    Deadline mark() {
        return new Deadline(this.description, this.due, true);
    }

    @Override
    Deadline unmark() {
        return new Deadline(this.description, this.due, false);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + due + ")";
    }
}
