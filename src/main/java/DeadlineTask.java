public class DeadlineTask extends Task {
    String dueDateTime;

    private DeadlineTask(boolean isDone, String description, String dueDateTime) {
        super(isDone, description);
        this.dueDateTime = dueDateTime;
        this.isDone = false;
        // add exceptions for time
    }

    public static DeadlineTask makeDeadline(String allDetails) throws IllegalArgumentException {
        if (allDetails.length() == 0) {
            throw new IllegalArgumentException("Deadline description cannot be empty.");

        }

        String[] descriptionDueTime = allDetails.split("/by");
        if (descriptionDueTime.length <= 1) {
            throw new IllegalArgumentException("Deadline due date cannot be empty.");
        }

        String description = descriptionDueTime[0];
        String dueDateTime = descriptionDueTime[1];
        return new DeadlineTask(false, description, dueDateTime);
    }

    public static DeadlineTask makeDeadline(String[] taskInputs) throws IllegalArgumentException {
        if (taskInputs.length != 4) {
            throw new IllegalArgumentException("Wrong number of arguments." +
                    "Current number of arguments: " + taskInputs.length);
        }

        if (taskInputs[2].length() == 0) {
            throw new IllegalArgumentException("Deadline description cannot be empty.");
        }

        String description = taskInputs[2];
        String dueDateTime = taskInputs[3];
        boolean isDone = taskInputs[1].equals("1");
        return new DeadlineTask(isDone, description, dueDateTime);
    }

    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "]" + this.description
                + "(by:" + dueDateTime + ")";
    }

    @Override
    public String toSavedString() {
        String done = isDone ? "1" : "0";
        return "E|" + done + "|" + this.description + "|" + this.dueDateTime +"\n";
    }
}
