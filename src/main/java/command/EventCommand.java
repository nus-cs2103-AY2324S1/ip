package command;

import task.Deadline;
import task.Task;
import task.TaskList;
import task.Event;

public class EventCommand extends Command {
    private String taskDetails;
    private String timeDetails;

    private Task currentTask;
    public EventCommand(String[] details) {
        if (details.length != 3) {
            System.out.println("Event needs to have a due date!");
        }
        taskDetails = details[0].replace("event ", "");
        String startTime = details[1].replace("from ", "");
        String endTime = details[2].replace("to ", "");
        timeDetails = "from: " + startTime + "to: " + endTime;
    }
    String commandMessage = "";
    @Override
    public String getCommandMessage() {
        return commandMessage;
    }
    @Override
    public void execute(TaskList taskList) {
        currentTask = new Event(taskDetails, timeDetails);
        taskList.addTask(currentTask);
        commandMessage = "Okay! I have added this task" + "\n" +
                currentTask.toString() + "\n" +
                    String.format("Now you have %d tasks!", taskList.getLength());
    }
}
