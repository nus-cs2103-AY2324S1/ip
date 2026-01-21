package veneto.command;

import veneto.task.TaskList;

public abstract class Command {
    /**
     * the Command operates
     * @param tasks the TaskList that the Command operates on
     */
    public abstract void op(TaskList tasks);

    /**
     * @return the type of the Command
     */
    public String getType() {return null;}
}
