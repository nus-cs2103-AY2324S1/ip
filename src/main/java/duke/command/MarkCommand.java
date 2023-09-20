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

    public MarkCommand(String[] details) {
        taskIndex = Integer.valueOf(details[0].replace("mark ", ""));
    }

    @Override
    public String getCommandMessage() {
        return commandMessage;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws KoraException {
        Task currentTask = taskList.getTask(taskIndex);
        currentTask.setMarked();
        storage.saveTaskList(taskList);
        commandMessage = "Wow you are done!" + "\n" + currentTask.toString();
    }
}
