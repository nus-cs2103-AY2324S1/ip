public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toData() {
        String done = String.valueOf(this.isDone ? 1 : 0);
        return "E | " + done + " | " + this.description + " | " + this.from + " | " + this.to;
    }

    public static Task dataToTask(String taskData) {
        int firstSplitIndex = taskData.indexOf("|");
        int secondSplitIndex = taskData.indexOf("|", firstSplitIndex + 1);
        int thirdSplitIndex = taskData.indexOf("|", secondSplitIndex + 1);
        boolean isDone = taskData.substring(0, firstSplitIndex - 1).equals("1");
        String desc = taskData.substring(firstSplitIndex + 2, secondSplitIndex - 1);
        String from = taskData.substring(secondSplitIndex + 2, thirdSplitIndex - 1);
        String to = taskData.substring(thirdSplitIndex + 2);
        Event newEvent = new Event(desc, from, to);
        newEvent.isDone = isDone;
        return newEvent;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + this.from
                + " to: " + this.to + ")";
    }
}
