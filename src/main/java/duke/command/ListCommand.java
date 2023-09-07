package duke.command;
import duke.TaskList;
import duke.Ui;

/**
 * Display all tasks in the current task list
 */

public class ListCommand extends Command {
    /**
     * Shows all tasks in the current task list.
     * @param taskList the existing task list of the user
     * @param ui the ui that handles successful/unsuccessful messages
     */

    public String execute(TaskList taskList, Ui ui){
        return ui.showListMessage(taskList);
    }
}
