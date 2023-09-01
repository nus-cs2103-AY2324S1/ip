package jarvis;

public class Deadline extends Task {

    protected String dueDate;

    public Deadline(String title, String dueDate, boolean isCompleted) {
        super(title, isCompleted);
        this.dueDate = dueDate;
    }
    
    @Override
    public String toString() {
        return "D | " + super.toString() + " | " + dueDate;
    }
}
