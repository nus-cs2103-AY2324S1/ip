class Deadline extends Task {
    private final String deadline;

    Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    Deadline(String name, String deadline, Boolean marked) {
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

    /**
     * Returns the name of task with deadline.
     *
     * @return Task to be done
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
