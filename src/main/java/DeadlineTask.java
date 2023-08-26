class DeadlineTask extends Task {
    public DeadlineTask(String description) throws DukeException {
        super(description);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " " + getDescriptionDetails();
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