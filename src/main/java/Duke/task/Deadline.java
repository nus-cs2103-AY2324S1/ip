package Duke.task;

import Duke.exception.EmptyTaskDescException;
import Duke.exception.InvalidTaskFormatException;

public class Deadline extends Task{
    final private String deadlineTime;

    public Deadline(String task) throws EmptyTaskDescException, InvalidTaskFormatException {
        super(task.split("/")[0]);
        String[] taskComponents = task.split("/");
        if(taskComponents.length != 2) {
            throw new InvalidTaskFormatException(task);
        }
        this.deadlineTime = super.insertColonInTime(taskComponents[1]);
    }

    private Deadline(String name, String time) throws EmptyTaskDescException {
        super(name);
        deadlineTime = time;
    }

    public static Deadline ParseContent(String content) throws EmptyTaskDescException {
        String[] components = content.split("\\|", 3);
        Deadline task = new Deadline(components[1], components[0]);
        if(components[2].equals("X"))
            task.SetCompleted();
        else
            task.SetUncompleted();
        return task;
    }

    public String toString() {
        return "[D]" + super.toString() + "(" + deadlineTime + ")";
    }
    public String toSaveFormat(){
        return "deadline:" + deadlineTime + "|" + super.toSaveFormat();
    }
}
