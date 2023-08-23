public class Deadlines extends Task {
    private String by;

    public Deadlines(String task, String details) {
        super(task);
        this.by = details.substring(3);
    }
    public String printDetails() {
        return String.format("(by: %s)", this.by);
    }
    @Override
    public String printTask() {
        return String.format("[D]%s%s", super.printTask(), this.printDetails());
    }
}
