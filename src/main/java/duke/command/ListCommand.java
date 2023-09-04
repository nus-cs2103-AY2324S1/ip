package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;
import duke.ui.GobbleChatContainer;

/**
 * R
 */
public class ListCommand extends Command {
    /**
     * Displays the list of tasks.
     *
     * @param taskList list of tasks
     * @param ui       user interface
     * @param storage  storage
     */
    @Override
    public void execute(TaskList taskList, GobbleChatContainer chat, Storage storage) {

//        ui.showTasks(taskList);
        chat.addMessage(taskList.toString(), "List");
    }
}
