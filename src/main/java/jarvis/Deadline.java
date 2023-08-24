package jarvis;

public class Deadline extends Task {

    protected String dueDate;

    public Deadline(String title, String dueDate) {
        super(title);
        this.dueDate = dueDate;
    }
    
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate + ")";
    }
}
