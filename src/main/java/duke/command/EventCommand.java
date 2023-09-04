package duke.command;

import duke.exception.KoraException;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Event;

public class EventCommand extends Command {
    private String taskDetails;
    private String startTimeDetails;
    private String endTimeDetails;

    private Task currentTask;
    public EventCommand(String[] details) throws KoraException {
        if (details.length != 3) {
            throw new KoraException("Event needs to have a due date!");
        }
        taskDetails = details[0].replace("event ", "");
        String startTime = details[1].replace("from ", "");
        String endTime = details[2].replace("to ", "");
        //timeDetails = "from: " + startTime + "to: " + endTime;
        startTimeDetails = startTime;
        endTimeDetails = endTime;
    }
    String commandMessage = "";
    @Override
    public String getCommandMessage() {
        return commandMessage;
    }
    @Override
    public void execute(TaskList taskList) throws KoraException {
        currentTask = new Event(taskDetails, startTimeDetails, endTimeDetails);
        taskList.addTask(currentTask);
        commandMessage = "Okay! I have added this task" + "\n" +
                currentTask.toString() + "\n" +
                    String.format("Now you have %d tasks!", taskList.getLength());
    }
}
