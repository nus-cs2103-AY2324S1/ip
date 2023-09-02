public class Events extends Task{

    final String taskChar = "[E]";
    protected String startDate = "";
    protected String endDate = "";
    protected String dateString = "";

    public Events(String taskName, String start, String end) {
        super(taskName);
        setDates(start, end);
    }

    public void setDates(String start, String end) {
        this.startDate = start;
        this.endDate = end;
        this.dateString = " (from: " + this.startDate + " to: " + this.endDate + ")";
    }

    @Override
    public String toFileString() {
        String taskDetails = super.getTaskName() + " | " + this.startDate + "-" + this.endDate;
        return "E | " + super.getStatusNumber() + " | " + taskDetails;
    }

    @Override
    public String toString() {
        return taskChar + super.toString() + this.dateString;
    }
}
