public class Event extends Task{

    protected String start;

    protected String end;

    /**
     * This is a constructor.
     *
     * @param description description of the task.
     * @param start the start time of the in a String format.
     * @param end the end time of the event in a Stirng format.
     */
    public Event (String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + start + " to: " + end + ")";
    }

    @Override
    public String saveString() {
        return super.saveString() + "/" + start + "/" + end;
    }
}
