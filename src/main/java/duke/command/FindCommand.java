package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    String searchTerm;

    public FindCommand(String searchTerm) {
        super(CommandType.FIND);
        this.searchTerm = searchTerm;
    }

    public void execute(TaskList tasks, Ui ui) {
        TaskList matchingTasks = tasks.findTasks(searchTerm);
        ui.listTasks(matchingTasks, true);
    }
}
