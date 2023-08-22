package command;

import task.Task;
import task.TaskList;

public class MarkCommand extends Command {

    String commandMessage = "";
    public MarkCommand() {

    }

    @Override
    public String getCommandMessage() {
        return commandMessage;
    }

    @Override
    public void execute(TaskList taskList, int taskIndex) {
        Task currentTask = taskList.getTask(taskIndex);
        currentTask.setMarked();
        commandMessage = "Wow you are done!" + "\n" + currentTask.showMarked() + currentTask.showDetails();
    }


}
