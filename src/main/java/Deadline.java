public class Deadline extends Task {
    protected String deadline;
    protected String description;

    public Deadline(String input) {
        super(input.split("/")[0]);
        try {
            this.deadline = input.split("/by ")[1];
        } catch (NullPointerException e) {
            throw new KieraException("write a date for your deadline in the form: /by (date)");
        }
    }

    public String getDeadline() {
        return this.deadline;
    }

    public String toStorageString() {
        return "D // " + this.getStatusIcon() + " // " + this.description + " /by " + this.deadline;
    }
    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "] " + this.description + " (by: " + this.deadline + ")";
    }
}
