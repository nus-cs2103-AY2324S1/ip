package Duke.task;

import Duke.exception.EmptyTaskDescException;
public class Event extends Task {
    final private String startTime;
    final private String endTime;
    public Event(String task) throws EmptyTaskDescException {
        super(task.split("/")[0]);
        String[] taskComponents = task.split("/");
        this.startTime = super.insertColonInTime(taskComponents[1]);
        this.endTime = super.insertColonInTime(taskComponents[2]);
    }

    private Event(String name, String startTime, String endTime) throws EmptyTaskDescException {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static Event ParseContent(String content) throws EmptyTaskDescException {
        String[] components = content.split("\\|", 4);
        Event task = new Event(components[2], components[0], components[1]);
        if(components[3].equals("X"))
            task.SetCompleted();
        else
            task.SetUncompleted();
        return task;
    }

    public String toString() {
        return "[E]" + super.toString() + "(" + startTime + " " + endTime + ")";
    }
    public String toSaveFormat(){
        return "event:v" + startTime + "|" + endTime + "|" + super.toSaveFormat();
    }
}
