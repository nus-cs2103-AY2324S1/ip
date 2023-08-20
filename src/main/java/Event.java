public class Event extends Task {
    private String startDate;
    private String endDate;

    public Event(String name, String startDate, String endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getType() {
        return "E";
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String toString() {
        return "[" + this.getType() + "]" + "[" + this.getStatus() + "]" + this.getName()
                + " (from: " + this.getStartDate() + " to: " + this.getEndDate() + ")";
    }
}
