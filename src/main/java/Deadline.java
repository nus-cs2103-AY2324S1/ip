public class Deadline extends Task {
    private final String deadlineTime;

    public Deadline(String description, String deadlineTime) {
        super(description);
        this.deadlineTime = deadlineTime;
    }

    public String writeTaskToFile() {
        return String.format("%s | %s | %s | %s", "D",
                this.getIsDone() ? 1 : 0,
                this.getDescription(),
                this.deadlineTime);
    }

    public static Deadline readTaskFromFile(String[] args) {
        Deadline newDeadlineTask = new Deadline(args[2], args[3]);
        if (args[1].equals("1")) {
            newDeadlineTask.markAsDone();
        }
        return newDeadlineTask;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadlineTime);
    }
}
