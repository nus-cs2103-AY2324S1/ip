public class Event extends Task{

    private String startDetails;
    private String endDetails;

    public Event(String task, String startDetails, String endDetails) {
        super(task);
        this.startDetails = startDetails;
        this.endDetails = endDetails;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startDetails + " to: " + endDetails + ") ";
    }
}
