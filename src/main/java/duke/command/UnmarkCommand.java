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

    public UnmarkCommand(String[] details) {
        taskIndex = Integer.parseInt(details[0].replace("unmark ", ""));
    }

    @Override
    public String getCommandMessage() {
        return commandMessage;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws KoraException {
        Task currentTask = taskList.getTask(taskIndex);
        currentTask.setUnmarked();
        storage.saveTaskList(taskList);
        commandMessage = "Wow you are not done!" + "\n" + currentTask.toString();
    }
}
