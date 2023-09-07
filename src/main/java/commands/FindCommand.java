package commands;

import java.util.ArrayList;

import data.TaskList;
import data.exception.DukeException;
import data.tasks.Task;
import storage.Storage;
import ui.UiCli;
import ui.UiMessage;

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
    public UiMessage execute(TaskList tasks, Storage storage, UiCli uiCli) throws DukeException {
        ArrayList<Task> result = tasks.findTasks(keyword);

        int count = result.size();
        if (count == 0) {
            return new UiMessage(new String[] {
                "Nothing found with keyword: " + keyword
            });
        }

        String[] displayArr = new String[result.size() + 1];
        displayArr[0] = String.format(
            "I've found %d tasks(s) that matches the keyword: %s",
            count,
            keyword
        );
        for (int i = 1; i < result.size() + 1; i++) {
            displayArr[i] = "  " + result.get(i - 1).toString();
        }

        return new UiMessage(displayArr);
    }
}
