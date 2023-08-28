package task;

public class Event extends Task {
    private String startDate, endDate;
    public Event(String name, String startDate, String endDate) {
        this.name = name;
        this.status = false;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Event(String name, boolean status, String startDate, String endDate) {
        this.name = name;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String getTimeDescriptor() {
        return String.format("%s to %s", startDate, endDate);
    }

    @Override
    public String toString() {
        String statusMark = this.status ? "[✓]" : "[✕]";
        return String.format("[E]%s %s (from: %s to: %s)", statusMark, name, startDate, endDate);
    }
}
