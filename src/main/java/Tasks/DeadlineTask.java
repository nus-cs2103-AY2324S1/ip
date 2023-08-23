package Tasks;

public class DeadlineTask extends Task {
    private String deadline = "";
    public DeadlineTask(String itemName, String deadline) {
        super(itemName);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
