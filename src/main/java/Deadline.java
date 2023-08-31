public class Deadline extends Task {

    protected String dueDate;

    public Deadline(String description, boolean isDone, String dueDate) {

        super(description, isDone);
        this.dueDate = dueDate;
    }

    @Override
    public String contentLine() {
        return "D" + super.contentLine() + "/" + this.dueDate;
    }

    @Override
    public String toString() {

        String result = "[D]" + super.toString() + "(by: " + this.dueDate + ")";
        return result;
    }
}
