public class Deadline extends Task{

    protected String dueBy;
    public Deadline(String description, String dueBy) {
        super(description);
        this.dueBy = dueBy.split(" ", 2)[1];
    }

    @Override
    public String toString() {
        String ret = "[D] " + super.toString() + " (by: " + this.dueBy + ")" ;
        return ret;
    }
}
