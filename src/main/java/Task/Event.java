package Task;

public class Event extends Task {
    final private String startTime;
    final private String endTime;
    public Event(String task) throws DukeException.EmptyTaskDescException {
        super(task.split("/")[0]);
        String[] taskComponents = task.split("/");
        this.startTime = super.insertColonInTime(taskComponents[1]);
        this.endTime = super.insertColonInTime(taskComponents[2]);
    }

    public String toString() {
        return "[E]" + super.toString() + "(" + startTime + " " + endTime + ")";
    }
}
