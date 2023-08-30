class EventTask extends Task {
    public EventTask(String description, boolean isDone) throws DukeException {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " " + getDescriptionDetails();
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    private String getDescriptionDetails() {
        String[] details = description.split(" /from ", 2);
        if (details.length == 2) {
            String[] eventParts = details[1].split(" /to ", 2);
            if (eventParts.length == 2) {
                return details[0] + " (from: " + eventParts[0] + " to: " + eventParts[1] + ")";
            } else {
                return details[0] + " (from: " + eventParts[0] + ")";
            }
        } else {
            String[] eventParts = description.split(" /by ", 2);
            if (eventParts.length == 2) {
                return eventParts[0] + " (by: " + eventParts[1] + ")";
            } else {
                return eventParts[0] + "";
            }
        }
    }
}
