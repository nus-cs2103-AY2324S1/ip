public class Deadline extends Task {
    private String by;

    public Deadline(String task, String by) {
        super(task);
        this.by = by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }

    public void print() {
        System.out.println(Duke.horizontalLine + "Got it. I've added this task:\n " + this.toString()+ "\n"
                + "Now you have " + Task.getCounter() + " tasks in the list\n" + Duke.horizontalLine);
    }
}
