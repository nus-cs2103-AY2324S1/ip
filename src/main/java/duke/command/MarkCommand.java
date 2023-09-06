package duke.command;

import duke.exception.KoraException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

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
        storage.saveTask(taskList);
        commandMessage = "Wow you are done!" + "\n" + currentTask.toString();
    }
}
