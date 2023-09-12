package command;

import duke.Storage;
import duke.Ui;
import task.TaskList;

public class HelpCommand extends Command {

    @Override
    public String execute(TaskList task, Storage storage) {
        return Ui.helpMessage();
    }
}
