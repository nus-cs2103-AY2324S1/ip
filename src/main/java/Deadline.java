public class Deadline extends Task {

    private String deadline;

    public Deadline(String title, String deadline) throws DukeException {
        super(title);
        if (deadline.isBlank()) {
            throw new DukeException("    Deadline cannot be blank...\n--------------------------------");
        }
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return "Deadline";
    }
    @Override
    public String getStatus() {
        return "[D]" + super.getStatus() + " (by: " + this.deadline + ")";
    }


}
