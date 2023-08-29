package duke.commands;

import duke.tasks.TaskList;

public abstract class Command {

    protected final CommandType commandType;
    protected final TaskList taskList;
    protected final String args;

    public Command(CommandType commandType, TaskList taskList, String args) {
        this.commandType = commandType;
        this.args = args;
        this.taskList = taskList;
    }

    public abstract void execute();

    public CommandType getCommandType() {
        return this.commandType;
    }
}
