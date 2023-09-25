package duke.command;

import duke.exception.KoraException;
import duke.list.TaskList;
import duke.storage.Storage;
import duke.task.Task;

/**
 * Subclass of Command class. Mark task to be completed.
 */
public class MarkCommand extends Command {

    private String commandMessage = "";
    private int taskIndex;

    /**
     * Class constructor of MarkCommand
     * @param details Task index that is to be marked done.
     */
    public MarkCommand(String[] details) {
        taskIndex = Integer.valueOf(details[0].replace("mark ", ""));
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
     * Marks specified task to be done.
     * @param taskList List with tasks.
     * @param storage Storage where tasks are stored.
     * @throws KoraException When there is error saving edited task list in Storage.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) throws KoraException {
        Task currentTask = taskList.getTask(taskIndex);
        currentTask.setMarked();
        storage.saveTaskList(taskList);
        commandMessage = "Wow you are done!" + "\n" + currentTask.toString();
    }
}
