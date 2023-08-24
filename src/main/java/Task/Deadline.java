package Task;

public class Deadline extends Task{
    final private String deadlineTime;

    public Deadline(String task) {
        super(task.split("/")[0]);
        String[] taskComponents = task.split("/");
        this.deadlineTime = super.insertColonInTime(taskComponents[1]);
    }

    public String toString() {
        return "[D]" + super.toString() + "(" + deadlineTime + ")";
    }
}
