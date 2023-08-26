public class DeadlineTask extends Task {
    String dueDateTime;

    public DeadlineTask(String input) throws IllegalArgumentException {
        super(input.split("/by")[0]);
        if (description.length() == 0) {
            throw new IllegalArgumentException("Event description cannot be empty.");
        }

        this.dueDateTime = input.split("/by")[1];
        // add exceptions for time
    }

    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "]" + this.description
                + "(by:" + dueDateTime + ")";
    }
}
