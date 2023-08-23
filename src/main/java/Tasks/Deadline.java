public class Deadline extends Task{
    private String dueDate;

    public Deadline(String title, String dueDate) {
        super(title);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        String mark = super.isMarked ? "[X] " : "[ ] ";
        return "[D]" + mark + title + " (by: " + this.dueDate + ")";
    }
}
