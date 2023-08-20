public class Deadline extends Task {
    Deadline(String task) {
        super(task);
    }

    Deadline(String task, boolean isDone) {
        super(task, isDone);
    }

    @Override
    public Deadline done() {
        return new Deadline(super.getTask(), true);
    }
    @Override
    public Deadline undone() {
        return new Deadline(super.getTask(), false);
    }


    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
