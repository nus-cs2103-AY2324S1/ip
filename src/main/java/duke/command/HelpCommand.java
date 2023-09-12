package duke.command;

import duke.task.TaskList;

import duke.ui.Ui;

public class HelpCommand extends Command {
    @Override
    public String execute(TaskList tasks) {
        return Ui.showHelpList();
    }
}