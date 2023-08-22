public class Deadline extends Task{

    private String by;

    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
    }

    @Override
    public String getTask() {
        return "Deadline ->" + super.getTask() + "By: " + this.by;
    }
}
