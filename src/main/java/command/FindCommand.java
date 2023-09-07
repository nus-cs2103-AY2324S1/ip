package command;

import dukeexception.DukeException;
import filestorage.FileStorage;
import list.TaskList;
import ui.Ui;

/**
 * A class that is part of the command, this will find task with similar words.
 */
public class FindCommand extends Command {
    private final String word;
    public FindCommand(String word) {
        this.word = word;
    }

    /**
     * A method that will excute the class.
     * @param tasklist contains all the past few tasks excuted.
     * @param ui contains the user interface that will be shown to the user depending on the inputs.
     * @param fileStorage Writing and reading on text files.
     * @throws DukeException if the word cannot be found.
     */
    public void excute(TaskList tasklist, Ui ui, FileStorage fileStorage) throws DukeException {
        TaskList newList = new TaskList();
        for (int i = 0; i < tasklist.size(); i++) {
            if (tasklist.get(i).toString().contains(word)) {
                newList.add(tasklist.get(i));
            }
        }
        ui.showFoundResults(newList);
    }
}
