package duke.command;

import duke.exception.KoraException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

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
        storage.saveTask(taskList);
        commandMessage = "Wow you are not done!" + "\n" + currentTask.toString();
    }
}
