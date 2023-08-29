package duke.commands;

import duke.tasks.TaskList;

public class ExitCommand extends Command {

    public ExitCommand(TaskList taskList, String args) {
        super(CommandType.EXIT, taskList, args);
    }

    @Override
    public void execute() {
    }
}
