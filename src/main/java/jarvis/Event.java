package jarvis;

public class Event extends Task {

    private String dueDate;
    
    public Event(String title, String dueDate, boolean isCompleted) {
        super(title, isCompleted);
        this.dueDate = dueDate;
    }
    
    @Override
    public String toString() {
        return "E | " + super.toString() + " | " + dueDate;
    }
}
