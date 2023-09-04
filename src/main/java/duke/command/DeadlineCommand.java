package duke.command;

import duke.exception.KoraException;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.TaskList;

public class DeadlineCommand extends Command {
    private String taskDetails;
    private String timeDetails;
    public DeadlineCommand(String[] details) throws KoraException {
        if (details.length != 2) {
            throw new KoraException("Deadline needs to have a due date!");
        }
        taskDetails = details[0].replace("deadline ", "");
        timeDetails = details[1].replace("by ", "");
    }
    String commandMessage = "";
    @Override
    public String getCommandMessage() {
        return commandMessage;
    }
    @Override
    public void execute(TaskList taskList) throws KoraException {
        Task currentTask = new Deadline(taskDetails, timeDetails);
        taskList.addTask(currentTask);
        commandMessage = "Okay! I have added this task" + "\n" +
                currentTask.toString() + "\n" +
                String.format("Now you have %d tasks!", taskList.getLength());
    }
}
