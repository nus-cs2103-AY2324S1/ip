public class Deadline extends Task{
    protected String deadline;

    public Deadline (String list, String deadline ,TaskType type) {
        super(list, type);
        this.deadline = deadline;
    }
    //Check index is calling correct method in task
    @Override
    public String setMarked() throws DukeException{
        super.setMarked();
        return "Nice! I've marked this task as done:\n" + toString();
    }

    @Override
    public String setUnmarked() throws DukeException{
        super.setUnmarked();
        return "OK, I've marked this task as not done yet:\n" + toString();
    }
    @Override
    public String toString() {
        return super.toString() + "( by: " + this.deadline + ")";
    }
}
