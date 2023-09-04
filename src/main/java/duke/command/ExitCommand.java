package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;
import duke.ui.GobbleChatContainer;

/**
 * Represents a ExitCommand class that deals with the command to exit the program.
 */
public class ExitCommand extends Command {
    /**
     * Displays the bye message.
     *
     * @param taskList list of tasks.
     * @param ui       user interface.
     * @param storage  storage.
     */
    @Override
    public void execute(TaskList taskList, GobbleChatContainer chat, Storage storage) {
        chat.addMessage("Bye. Hope to see you again soon!", "Exit");
    }

    /**
     * Returns true as this is an exit command.
     *
     * @return true as this is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
