package dan.command;

import dan.task.Task;
import dan.task.TaskList;

public abstract class Command {

    public abstract void op(TaskList tasks);
    public static String getType() {return null;}
}
