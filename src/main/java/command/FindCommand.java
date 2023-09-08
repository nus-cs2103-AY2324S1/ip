package command;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Represents a command that searches the task list for a specific keyword.
 * This command when executed, will search through all the tasks present in the task list and
 * displays the ones that contain the specified keyword.
 */
public class FindCommand extends Command {
    /** The keyword to search for in tasks. */
    private String keyWord;

    /**
     * Creates a FindCommand with the keyword that the user is searching for.
     *
     * @param keyWord The keyword to search for in the task list.
     */
    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    /**
     * Executes the find command.
     * This method goes through the tasks in the taskList, and searches for the tasks that contain the keyword,
     * then invokes the UI to these tasks.
     *
     * @param tasks The list of task on which the command will operate on.
     * @param ui The UI which is used during the command execution to show find text.
     * @param storage The storage where tasks are stored and retrieved from, currently not utilized in this method but can be extended to use in the future.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFindText(tasks, this.keyWord);
    }

    /**
     * Specifies that this command is not an exit command.
     *
     * @return false, as this command does not cause the program to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
