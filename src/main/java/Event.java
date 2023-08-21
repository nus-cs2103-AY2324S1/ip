class Event extends Task{
    private final String timeFrom;
    private final String timeTo;
    public Event(String name, String timeFrom, String timeTo) {
        super(name);
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + timeFrom + " to: " + timeTo + ")";
    }
}
