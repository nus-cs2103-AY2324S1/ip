package duke;

/**
 * Special kind of task that has a description and a deadline
 */
class Deadline extends Task {
    protected String type = "D";
    protected String date;

    /**
     * Constructor for the Deadline class.
     * @param description Describes the task
     * @param date When the task should be completed by
     */
    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }
    @Override
    public void mark() {
        this.isDone = true;
        System.out.println("-------------------------------\n"
                + "Nice! I've marked this task as done:\n"
                + this
                + "\n-------------------------------");
    }
    @Override
    public void unmark() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:\n"
                + this);
    }
    @Override
    public String toString() {
        return this.type + " | " + this.getStatusIcon() + " | " + this.description
                + " | " + this.date;
    }
}