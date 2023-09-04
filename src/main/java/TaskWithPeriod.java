public abstract class TaskWithPeriod extends TaskWithDeadline {
    protected Date from;
    public TaskWithPeriod(String description, String from, String to) {
        super(description, to);
        this.from = new Parser().parseDate(from);
    }

    public Date getFrom() {
        return this.from;
    }

    public String getFromSave() {
        return this.from.toSave();
    }
}
