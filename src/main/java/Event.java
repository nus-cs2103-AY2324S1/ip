public class Event extends Task {

    protected String startData;
    protected String endDate;

    public Event(String description, String startDate, String endDate) {
        super(description);
        this.startData = startDate;
        this.endDate = endDate;
    }

    public Event() {
        super("");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startData + " to: " + endDate + ")";
    }

    public String toFileString() {
        return "E | "  + super.getStatusIcon() + " | " + description + " | " + startData + " | " + endDate;
    }

    public void fromFileString(String fileString) {
        String[] fileStringArray = fileString.split(" \\| ");
        this.setStatusIcon(fileStringArray[1]);
        this.description = fileStringArray[2];
        this.startData = fileStringArray[3];
        this.endDate = fileStringArray[4];
    }
}
