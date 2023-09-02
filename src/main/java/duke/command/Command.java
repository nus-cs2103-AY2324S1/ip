package duke.command;

import duke.TaskList;

public abstract class Command {
    protected TaskList tasks;

    public void setData(TaskList tasks) {
        this.tasks = tasks;
    }

    public boolean isBye() {
        return this instanceof ByeCommand;
    }

    public abstract String[] execute();
}
