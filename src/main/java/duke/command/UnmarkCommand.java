package duke.command;

import duke.exception.KoraException;
import duke.list.TaskList;
import duke.storage.Storage;
import duke.task.Task;

/**
 * Subclass of Command class. Unmark the task to be undone.
 */
public class UnmarkCommand extends Command {

    private String commandMessage = "";
    private int taskIndex;

    /**
     * Class constructor of UnmarkCommand.
     * @param details Task index that is to be unmarked as undone.
     */
    public UnmarkCommand(String[] details) {
        taskIndex = Integer.parseInt(details[0].replace("unmark ", ""));
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public String getCommandMessage() {
        return commandMessage;
    }

    /**
     * {@inheritDoc}
     *
     * Unmarks specified task to be undone.
     * @param taskList List with tasks.
     * @param storage Storage where tasks are stored.
     * @throws KoraException When there is error saving edited task list in Storage.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) throws KoraException {
        Task currentTask = taskList.getTask(taskIndex);
        currentTask.setUnmarked();
        storage.saveTaskList(taskList);
        commandMessage = "Wow you are not done!" + "\n" + currentTask.toString();
    }
}
