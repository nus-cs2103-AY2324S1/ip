package duke.command;

import duke.task.TaskList;
import duke.task.Task;

public class MarkCommand extends Command {

    String commandMessage = "";
    private int taskIndex;
    public MarkCommand(String[] details) {

        taskIndex = Integer.valueOf(details[0].replace("mark ", ""));
    }

    @Override
    public String getCommandMessage() {

        return commandMessage;
    }

    @Override
    public void execute(TaskList taskList) {
        Task currentTask = taskList.getTask(taskIndex);
        currentTask.setMarked();
        commandMessage = "Wow you are done!" + "\n" + currentTask.toString();
    }


}
