package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * FindCommand to search for a task in the taskList.
 */
public class FindCommand implements Command {
    @Override
    public boolean execute(TaskList tasks, Ui ui) throws DukeException {
        String userInput = ui.getLastMsg();
        String keyword = userInput.substring(4).trim();
        if (keyword.trim().length() == 0) {
            throw new DukeException("Please provide a keyword.\nUse [list] instead if you want to see the whole list instead.");
        }
        ui.respond("Here are the tasks that contains the keyword: " + keyword + "\n" + tasks.find(keyword));
        return false;
    }
}
