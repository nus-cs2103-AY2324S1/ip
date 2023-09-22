package jarvis.commands;

import java.util.ArrayList;

import jarvis.exceptions.InvalidIndexException;
import jarvis.exceptions.InvalidTaskFormatException;
import jarvis.gui.Ui;
import jarvis.storage.Storage;
import jarvis.tasks.Task;
import jarvis.tasks.TaskList;

/**
 * Represents a command to sort deadline tasks by their dueDate.
 */
public class SortDeadlineCommand implements Command {

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage)
            throws InvalidIndexException, InvalidTaskFormatException {
        ArrayList<Task> sortedDeadlines = taskList.sortTaskByDueDate();
        storage.saveTasks(sortedDeadlines);
        return ui.printFilteredTasks("Deadline by due date", sortedDeadlines);
    }
}
