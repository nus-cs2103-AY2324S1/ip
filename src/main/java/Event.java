public class Event extends Task {
    private String from;
    private String to;
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "E" + super.toString() + " | " + this.from + "-" + this.to;
    }


    @Override
    public String toFileString() {
        char taskType = 'E';
        return taskType + " | " + super.toFileString() + " | " + from + " - " + to;
    }


    public static Event createEventFromData(String taskData) {
        String[] taskParts = taskData.split("\\s*\\|\\s*");

        if (taskParts.length >= 4 && taskParts[0].trim().equals("E")) {
            String doneStatus = taskParts[1].trim();
            String description = taskParts[2].trim();
            String fromTo = taskParts[3].trim(); // Combine from-to time as a single string

            // Split from-to time into separate 'from' and 'to' parts
            String[] fromToParts = fromTo.split("-");
            if (fromToParts.length >= 2) {
                String from = fromToParts[0].trim();
                String to = fromToParts[1].trim();

                Event event = new Event(description, from, to);
                if (doneStatus.equals("1")) {
                    event.markDone();
                }
                return event;
            }
        }

        return null; // incomplete data.txt
    }

}
