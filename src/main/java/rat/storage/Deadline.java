package rat.storage;

public class Deadline extends Task {

    private String deadline;

    protected Deadline(String deadline, String name) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String taskType = "[D]";
        return taskType + super.toString() + " (by: " + this.deadline + ")";
    }

}
