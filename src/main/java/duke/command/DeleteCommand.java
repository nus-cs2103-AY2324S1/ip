package duke.command;

import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    private int taskIndex;
    public DeleteCommand(String[] details) {

        taskIndex = Integer.valueOf(details[0].replace("delete ", ""));
    }
    String commandMessage = "";

    @Override
    public String getCommandMessage() {

        return commandMessage;
    }

    @Override
    public void execute(TaskList taskList) {
        Task currentTask = taskList.getTask(taskIndex);
        String taskDetails = currentTask.toString();
        taskList.removeTask(taskIndex);
        commandMessage = "Okay. I have removed this task" + "\n"
                + taskDetails + "\n" + String.format("Now you have %d tasks!", taskList.getLength());
    }
}
