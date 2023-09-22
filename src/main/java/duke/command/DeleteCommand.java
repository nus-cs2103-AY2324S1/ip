package duke.command;

import duke.exception.KoraException;
import duke.list.TaskList;
import duke.storage.Storage;
import duke.task.Task;

/**
 * Subclass of Command class. Deletes a task from task list.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;
    private String commandMessage = "";

    /**
     * Class constructor of DeleteCommand
     * @param details Task index to delete from task list.
     */
    public DeleteCommand(String[] details) {
        taskIndex = Integer.valueOf(details[0].replace("delete ", ""));
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
     * Deletes task from task list.
     * @param taskList List with tasks.
     * @param storage Storage where tasks are stored.
     * @throws KoraException When there is error saving edited task list in Storage.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) throws KoraException {
        Task currentTask = taskList.getTask(taskIndex);
        String taskDetails = currentTask.toString();
        taskList.removeTask(taskIndex);
        storage.saveTaskList(taskList);
        commandMessage = "Okay. I have removed this task" + "\n"
                + taskDetails + "\n" + String.format("Now you have %d tasks!", taskList.getLength());
    }
}
