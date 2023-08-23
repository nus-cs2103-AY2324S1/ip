package command;

import task.Task;
import task.Deadline;
import task.TaskList;

public class DeadlineCommand extends Command {
    private String taskDetails;
    private String timeDetails;
    public DeadlineCommand(String[] details) {
        if (details.length != 2) {
            System.out.println("Deadline needs to have a due date!");
        }
        taskDetails = details[0].replace("deadline ", "");
        timeDetails = "by: " + details[1].replace("by ", "");
    }
    String commandMessage = "";
    @Override
    public String getCommandMessage() {
        return commandMessage;
    }
    @Override
    public void execute(TaskList taskList) {
        Task currentTask = new Deadline(taskDetails, timeDetails);
        taskList.addTask(currentTask);
        commandMessage = "Okay! I have added this task" + "\n" +
                currentTask.toString() + "\n" +
                String.format("Now you have %d tasks!", taskList.getLength());
    }
}
