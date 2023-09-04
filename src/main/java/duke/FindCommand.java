package duke;

import duke.command.Command;
import duke.task.TaskList;
import duke.ui.GobbleChatContainer;

/**
 * Represents a FindCommand class that deals with the command to find a task.
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Constructor for FindCommand.
     *
     * @param keyword keyword to be searched for in the description.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Prints the list of tasks that contains the keyword.
     *
     * @param taskList list of tasks that contains the keyword.
     * @param ui       ui object to print the list of tasks.
     * @param storage  storage object to save the list of tasks.
     */
    @Override
    public void execute(TaskList taskList, GobbleChatContainer chat, Storage storage) {
//        ui.printResultList(taskList.findTask(this.keyword));
        chat.addMessage(taskList.findTask(this.keyword).toString(), "Find");
    }
}
