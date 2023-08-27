package extensions;

public class DeadlineTask extends Task {

    protected String deadline;

    public DeadlineTask(String desc, String deadline) {
        super(desc);
        this.deadline = deadline;
    }

    public DeadlineTask(String desc, String deadline, int isMarked) {
        super(desc);
        this.deadline = deadline;
        if (isMarked == 1) {
            this.isDone = true;
        }
    }

    @Override
    protected String getTextFormattedString() {
        return String.format("D|%d|%s|%s", this.isDone ? 1 : 0, this.desc, this.deadline);
    }

    @Override
    public String toString() {
        String output = String.format("[D]%s (by: %s)",
                super.toString(),
                this.deadline);
        return output;
    }

}
