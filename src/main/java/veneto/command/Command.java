package veneto.command;

import veneto.task.TaskList;

public abstract class Command {

    public abstract void op(TaskList tasks);
    public static String getType() {return null;}
}
