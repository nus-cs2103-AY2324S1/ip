public class Deadline extends Task {

    protected String dueDate;

    public Deadline(String description, String dueDate) {

        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {

        String result = "[D]" + super.toString() + "(by: " + this.dueDate + ")";
        return result;
    }
}
