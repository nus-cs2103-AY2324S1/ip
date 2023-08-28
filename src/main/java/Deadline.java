public class Deadline extends Task {
    private String deadline;
    public Deadline(String description) {
        super(description);
        // Split the input string by "/by" to separate description and deadline
        String[] splitString = description.split("/by", 2);
        // Should throw error if there are multiple "/by" or no "/by"
        if (splitString.length >=2) {
            this.description = splitString[0].trim();
            this.deadline = splitString[1].trim();
        }
    }

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String getStatusIcon() {
        return "[D]" + super.getStatusIcon();
    }

    @Override
    public String toString() {
        if (deadline == null) return super.toString();
        return super.toString() + String.format(" (by: %s)",deadline);
    }

    @Override
    public String toFileString() {
        return "D | " + super.toFileString() + (deadline == null ? "" : " | " + deadline);
    }
}
