package command;

import dukeexception.DukeException;
import filestorage.FileStorage;
import list.TaskList;
import ui.Ui;

/**
 * The class that will execute the Find similar word in Tasks command.
 * This class extends from the Command class.
 */
public class FindCommand extends Command {
    private final String word;

    /**
     * Constructs the class.
     *
     * @param word The word used to find similarity in classes.
     */
    public FindCommand(String word) {
        this.word = word;
    }

    /**
     * Executes the Command of finding task that has the similar words.
     *
     * @param taskList The TaskLIst that contains all the tasks.
     * @param ui The user interface that will be shown to the user.
     * @param fileStorage The File that will be written and read from.
     * @throws DukeException If user inputs is invalid.
     */
    public String execute(TaskList taskList, Ui ui, FileStorage fileStorage) throws DukeException {
        TaskList newList = new TaskList();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).toString().contains(word)) {
                newList.add(taskList.get(i));
            }
        }
        return ui.showFoundResults(newList);
    }
}
