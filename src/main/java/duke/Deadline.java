package duke;

/**
 * Special kind of task that has a description and a deadline
 */
class Deadline extends Task {
    /**
     * Signifies the specific type of task.
     */
    protected String type = "D";
    /**
     * The date where the task should be completed by.
     */
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

    /**
     * Mark this Deadline task as done.
     */
    @Override
    public void mark() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:\n"
                + this);
    }

    /**
     * Unmark this Deadline task as not done yet.
     */
    @Override
    public void unmark() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:\n"
                + this);
    }

    /**
     * Description of this Deadline task.
     * @return String containing the all the information of this Deadline task
     */
    @Override
    public String toString() {
        return this.type + " | " + this.getStatusIcon() + " | " + this.description
                + " | " + this.date;
    }
}