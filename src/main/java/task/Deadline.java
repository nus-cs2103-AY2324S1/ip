package task;

public class Deadline extends Task {
    private final String deadline;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    public Deadline(String name, String deadline, Boolean marked) {
        super(name, marked);
        this.deadline = deadline;
    }


    @Override
    public Deadline mark() {
        return new Deadline(this.name, this.deadline, true);
    }

    @Override
    public Deadline unmark() {
        return new Deadline(this.name, this.deadline, false);
    }

    @Override
    public String saveTask() {
        return String.format("%s | %s", super.saveTask(), this.deadline);
    }

    /**
     * Returns the name of task with deadline.
     *
     * @return Task.Task to be done
     */
    @Override
    public String toString() {
        return String.format(
                "[D]%s (by: %s)",
                super.toString(),
                this.deadline
        );
    }
}
