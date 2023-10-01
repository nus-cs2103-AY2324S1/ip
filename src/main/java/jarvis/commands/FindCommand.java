package jarvis.commands;

import java.util.ArrayList;
import java.util.stream.Collectors;

import jarvis.exceptions.InvalidTaskFormatException;
import jarvis.ui.Ui;
import jarvis.storage.Storage;
import jarvis.tasks.Task;
import jarvis.tasks.TaskList;

/**
 * Represents a command to find and display tasks containing a specific keyword in their titles.
 */
public class FindCommand implements Command {

    private String userInput;

    public FindCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the FindCommand to search for tasks containing the specified keyword in their titles.
     *
     * @param taskList The TaskList containing the tasks to search within.
     * @param ui       The Ui to display responses and results.
     * @param storage  The Storage for saving tasks (not used in this command).
     * @throws InvalidTaskFormatException If an invalid task format is encountered.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidTaskFormatException {
        if (userInput.equalsIgnoreCase("event")) {
            throw new InvalidTaskFormatException(null);
        }
        assert taskList != null && ui != null && storage != null;
        ArrayList<Task> foundTasks = findTasks(taskList);
        return getResponse(ui, foundTasks);
    }

    private ArrayList<Task> findTasks(TaskList taskList) {
        int indexOfFind = userInput.indexOf("find");
        String keyword = userInput.substring(indexOfFind + 4).trim().toLowerCase();

        return taskList.getTaskList()
                .stream()
                .filter(task -> task.getTitle().toLowerCase().contains(keyword))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private static String getResponse(Ui ui, ArrayList<Task> foundTasks) {
        if (foundTasks.isEmpty()) {
            return ui
                    .printResponse("No matching task is found, Master. "
                            + "Please check your spelling or use another word");
        } else {
            return ui.printTasks(foundTasks);
        }
    }
}
