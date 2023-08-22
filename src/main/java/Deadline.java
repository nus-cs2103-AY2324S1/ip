public class Deadline extends Task{
    protected String deadline;

    public Deadline (String list, String deadline) {
        super(list);
        this.deadline = deadline;
    }
    //Check index is calling correct method in task
    @Override
    public String setMarked() {
        super.setMarked();
        return "Nice! I've marked this task as done:\n" + toString();
    }

    @Override
    public String setUnmarked() {
        super.setUnmarked();
        return "OK, I've marked this task as not done yet:\n" + toString();
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + "( by: " + this.deadline + ")";
    }
}
