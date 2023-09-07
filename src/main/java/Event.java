public class Event extends Task {
    private String from;
    private String to;

    public Event(String task, String from, String to) {
        super(task);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getStatus(){
        String time = "(from: " + from + " to: " + to + ")";
        return "[Event]" + super.getStatus() + " " + time;
    }
}