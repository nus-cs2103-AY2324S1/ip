public class Deadline extends SingleTask {
    String by;
    public Deadline(String description, String deadline) {
        super(description);
        this.by = deadline;
    }

    @Override
    public String toString() {
        return "OK DONE ALR added your Deadline ah:\n" +
                "[D][" + super.getStatusIcon() + "] " + description +"(by: "+ by + ")";
    }
    @Override
    public String listString() {
        return ". [D][" + super.getStatusIcon() + "] " + description +"(by: "+ by + ")";
    }
}
