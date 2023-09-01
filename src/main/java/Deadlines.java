public class Deadlines extends Task {
    protected String endDate;

    public Deadlines (String description, String endDate) {
        super(description);
        this.endDate = endDate;
    }

    @Override
    public String getTypeIcon() {
        return "[D]";
    }

    @Override
    public String printTask() {
        return this.getTypeIcon() + this.getStatusIcon() + this.description + " (by: " + this.endDate + ")";
    }
}
