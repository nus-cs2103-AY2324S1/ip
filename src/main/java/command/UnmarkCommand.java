package command;

import task.Task;
import task.TaskList;

public class UnmarkCommand extends Command {
    public UnmarkCommand() {

    }

    String commandMessage = "";
    @Override
    public String getCommandMessage() {
        return commandMessage;
    }
    @Override
    public void execute(TaskList taskList, int taskIndex) {
        Task currentTask = taskList.getTask(taskIndex);
        currentTask.setUnmarked();
        commandMessage = "Faster do!" + "\n" + currentTask.showMarked() + currentTask.showDetails();
    }
}
