package task;

public class Deadline extends Task {
    private String dueDate;
    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        String output = String.format("[D][%s] %s (by: %s)", super.getStatusIcon(), description, dueDate);
        return output;
    }
}
