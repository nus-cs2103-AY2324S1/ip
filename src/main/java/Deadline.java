public class Deadline extends Task {
    private String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String getTypeIcon() {
        return "[D]";
    }

    @Override
    public String getExtras() {
        return "(by:" + date + ")";
    }

    @Override
    public String toString() {
        return getTypeIcon() + super.getStatusIcon() +
                    super.description + getExtras();
    }
}