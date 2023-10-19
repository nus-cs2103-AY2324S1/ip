package qi.command;

import qi.storage.Storage;
import qi.tasklist.TaskList;
import qi.ui.Ui;

/**
 * Represents the process of find tasks that match with a key word.
 */
public class FindCommand extends Command {
    private final String keyWord;

    /**
     * Takes in the key word to find task.
     *
     * @param keyWord Key word to find task.
     */
    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    /**
     * Finds the tasks containing the key word.
     *
     * @param tasks TaskList recording the current task in the list.
     * @param ui Ui representing the current User Interface (UI).
     * @param storage Storage updating data if necessary.
     * @return String notification created by Ui.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showMatching(tasks.matchingKeyWord(this.keyWord));
    }
}

