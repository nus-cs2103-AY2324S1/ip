package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The FindCommand is for "find" command.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * The constructor for a FindCommand.
     *
     * @param keyword The string representation of the keyword.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * This method is used to execute the find command.
     *
     * @param tasks The TaskList to be worked on.
     * @param ui The Ui to be worked on.
     * @param storage The Storage to be worked on.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks.find(this.keyword).getAllTasks(), "Here are the matching tasks in your list:");
    }

    /**
     * This method is used to check whether it is an exit command.
     *
     * @return Returns false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
