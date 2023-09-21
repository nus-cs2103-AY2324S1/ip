package duke.command;

import duke.task.TaskList;

import duke.ui.Ui;

public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks) {
        return Ui.exit();
    }
}
