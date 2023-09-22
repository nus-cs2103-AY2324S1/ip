package tasket.command;

import java.util.ArrayList;

import tasket.data.TaskList;
import tasket.exception.TasketException;
import tasket.storage.Storage;
import tasket.ui.Ui;

/**
 * The class for find command.
 */
public class FindCommand extends Command {

    /**
     * The constructor for find command.
     * @param description The keyword to find the matching tasks.
     */
    public FindCommand(String description) {
        super(description);
    }

    /**
     * Find the tasks that match the keyword.
     * After finding, show the results.
     *
     * @param taskList The task list instance of duke.
     * @param ui The ui instance of duke.
     * @param storage The storage instance of duke.
     * @return List of matching tasks.
     * @throws TasketException Does not throw the exception.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws TasketException {
        ArrayList<String> matchingTasks = new ArrayList<>();

        for (int i = 0; i < taskList.size(); i++) {
            String taskString = taskList.getTaskString(i);

            if (!taskString.contains(commandDescription)) {
                continue;
            }

            matchingTasks.add(String.format("%d.%s", i + 1, taskString));
        }

        return ui.showMatchingTasks(matchingTasks.toArray(new String[0]));
    }
}
