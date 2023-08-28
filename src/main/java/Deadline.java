public class Deadline extends Task {
    private String deadline;

    public Deadline(String detail, String deadline) {
        super(detail);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format(
            "[D]%s (by: %s)",
            super.toString(), 
            this.deadline
        );
    }

    @Override
    public String toFileFormatString() {
        return String.format(
            "D|%s|%s",
            super.toFileFormatString(),
            this.deadline
        );
    }
}
