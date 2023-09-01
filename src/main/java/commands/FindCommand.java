package commands;

import java.util.ArrayList;

import data.TaskList;
import data.exception.DukeException;
import data.tasks.Task;
import storage.Storage;
import ui.Ui;

/**
 * The FindCommand class.
 * Finds the list of tasks containing a given keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * The constructor method of the FindCommand class.
     * Takes in a keyword (can be one or multiple) that
     * will be used to find a list of tasks containing it.
     *
     * @param keyword The keyword to be used to find
     *                a list of tasks that contains it.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        ArrayList<Task> result = tasks.findTasks(keyword);

        int count = result.size();
        if (count == 0) {
            ui.displayMsg("Nothing found with keyword: " + Ui.cTxt(keyword, Ui.Color.YELLOW));
            return;
        }

        String[] displayArr = new String[result.size() + 1];
        displayArr[0] = "I've found " 
            + Ui.cTxt(String.valueOf(count), Ui.Color.YELLOW)
            + " task(s) that matches the keyword: " 
            + Ui.cTxt(keyword, Ui.Color.YELLOW);
        for (int i = 1; i < result.size() + 1; i++) {
            displayArr[i] = "  " + result.get(i - 1).toString();
        }

        ui.displayMsg(displayArr);
    }
}
