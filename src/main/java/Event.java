public class Event extends Task {
    private String startTime;
    private String endTime;
    public Event(String description) {
        super(description);
        String[] fromParts = description.split("/from");
        String[] toParts = description.split("/to");

        if (fromParts.length >= 2) {
            this.description = fromParts[0].split("/to")[0].trim();
            String[] aftFrom = fromParts[1].split("/to");
            this.startTime = aftFrom[0].trim();
            if (aftFrom.length >= 2) {
                // '/to' is after '/from'
                this.endTime = aftFrom[1].trim();
            } else {
                // '/to' is before '/from'
                this.endTime = fromParts[0].split("/to")[1].trim();
            }
        } else if (toParts.length >= 2) {
            String[] parts = toParts[1].split("/from");
            this.description = toParts[0].trim();
            this.endTime = parts[0].trim();
            if (parts.length > 1) {
                this.startTime = parts[1].trim();
            }
        }
    }

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String getStatusIcon() {
        return "[E]" + super.getStatusIcon();
    }

    @Override
    public String toString() {
        if (startTime == null && endTime == null) return super.toString();
        return super.toString() + String.format(" (from %s to %s)", startTime, endTime);
    }

    @Override
    public String toFileString() {
        return "E | "
                + super.toFileString()
                + (startTime == null && endTime == null
                        ? ""
                        : String.format(" | %s | %s", startTime, endTime));
    }
}
