package duke.command;

import duke.exception.KoraException;
import duke.list.TaskList;
import duke.storage.Storage;
import duke.task.Task;

/**
 * Subclass of Command class. Find tasks that matches the keyword given by user.
 */
public class FindCommand extends Command {
    private String keyword;
    private TaskList newTaskList = new TaskList();

    /**
     * Class constructor of FindCommand.
     * @param details Keyword to search for matching tasks.
     */
    public FindCommand(String[] details) {
        keyword = details[0].replace("find ", "");
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public String getCommandMessage() {
        if (newTaskList.getLength() == 0) {
            return "There are no matching task!";
        } else {
            return "Here are your matching tasks in your list: \n" + newTaskList.toString();
        }
    }

    /**
     * {@inheritDoc}
     *
     * Generates list with tasks that match the keyword.
     * @param taskList List with tasks.
     * @param storage Storage where tasks are stored.
     * @throws KoraException When there is error retrieving task from task list.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) throws KoraException {
        int taskListSize = taskList.getLength();
        for (int i = 0; i < taskListSize; i++) {
            Task task = taskList.getTask(i + 1);
            if (task.getDetails().contains(keyword)) {
                newTaskList.addTask(task);
            }
        }
    }
}
