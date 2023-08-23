public class Event extends Task{

    public Event(String event) {
        super(event);
    }
    @Override
    public String eventCode() {
        return "E";
    }

    @Override
    public String eventDescription() {
        String taskWithoutDates = super.eventDescription();
        int i = taskWithoutDates.indexOf('/');
        int j = taskWithoutDates.indexOf("/to");


        return String.format("%s (From: %s to: %s)",
                taskWithoutDates.substring(0, i-1),
                taskWithoutDates.substring(i+6, j -1),
                taskWithoutDates.substring(j+4)
                );
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.eventCode(), this.getStatusIcon(), this.eventDescription());
    }
}
