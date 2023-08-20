package extensions;

public class DeadlineTask extends Task {

    protected String deadline;

    public DeadlineTask(String desc, String deadline) {
        super(desc);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String output = String.format("[D]%s (by: %s)",
                super.toString(),
                this.deadline);
        return output;
    }

}
