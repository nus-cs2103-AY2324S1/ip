package duke;

/**
 * Special kind of task that only has a description
 */
class Todo extends Task {
    protected String type = "T";

    /**
     * Constructor for the Todo task type.
     * @param description String describing what the Todo task is about
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Mark this Todo task as done.
     */
    @Override
    public void mark() {
        this.isDone = true;

        System.out.println("-------------------------------\n"
                + "Nice! I've marked this task as done:\n"
                + this
                + "\n-------------------------------");

        System.out.println("Nice! I've marked this task as done:\n"
                + this);
    }
    /**
     * Unmark this Todo task as not done yet.
     */
    @Override
    public void unmark() {
        this.isDone = false;

        System.out.println("OK, I've marked this task as not done yet:\n"
                + this);
    }
    @Override
    public String toString() {
        return this.type + " | " + this.getStatusIcon()
                + " | " + this.description;
    }
}