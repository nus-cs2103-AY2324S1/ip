package duke.taskmanagement;
public class Event extends Task{
    protected String from;
    protected String till;

    public Event(String description, String from, String till) {
        super(description);
        this.from = from;
        this.till = till;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (from: " + from + " to: " + till + ")";
    }

    public String convertIsDone() {
        return super.isDone ? "1" : "0";
    }
    @Override
    public String saveToFileString(){
        return "T | " +  convertIsDone() + " | " + description + " | " + this.from + " " + this.till +"\n";
    }
}
