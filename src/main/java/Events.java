public class Events extends Task {
    protected String startDate;

    protected String endDate;
    public Events (String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String getTypeIcon() {
        return "[E]";
    }

    @Override
    public String printTask() {
        return this.getTypeIcon() + this.getStatusIcon() + this.description +
                " (from: " + this.startDate + " to: " + this.endDate + ")";
    }


}
