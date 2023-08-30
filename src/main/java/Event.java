public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, String from, String to, String status) {
        super(description);
        this.from = from;
        this.to = to;
        if(status.equals("Y")){
            super.taskStatusFromFile(true);
        } else {
            super.taskStatusFromFile(false);
        }
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + from + " to:" + to + ")";

    }

    @Override
    public String toFileString(){
        return "E" + super.toFileString() + "|" + from + "|" + to;
    }
}
