public class Deadline extends Task{

    protected String deadlineString;

    public Deadline(String description, String deadlineString) {
        super(description);
        this.deadlineString = deadlineString;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[D]");
        sb.append("[" + getStatusIcon() + "] ");
        sb.append(description);
        sb.append(" (by: " + deadlineString + ")");
        return sb.toString();
    }
}
