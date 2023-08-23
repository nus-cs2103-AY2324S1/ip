public class Event extends Task {
    protected String startDate;
    protected String endDate;
    Event(String name, String startDate, String endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getStartDate() {
        return this.startDate;
    }
    public String getEndDate() {
        return this.endDate;
    }
    public String identifier() {
        return "E";
    }
    @Override
    public String toString() {
        return String.format("[%s] [%s] %s (from: %s to: %s)", this.identifier(), this.showStatus(), this.showName(),
                this.getStartDate(), this.getEndDate());
    }
}
