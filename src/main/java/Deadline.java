public class Deadline extends Task {
    private String endTime;

    public Deadline (String name, String endTime) {
        super(name);
        this.endTime = endTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public String writeTaskToFile() {
        return String.format("%s | %s | %s | %s", "D",
                this.isDone() ? 1 : 0,
                this.getName(),
                this.getEndTime());
    }

    public static Deadline readTaskFromFile(String[] args) {
        Deadline newDeadlineTask = new Deadline(args[2], args[3]);
        if (args[1].equals("1")) {
            newDeadlineTask.markDone();
        }
        return newDeadlineTask;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.endTime);
    }
}
