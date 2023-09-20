package duke.command;

import duke.exception.KoraException;
import duke.list.TaskList;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;

/**
 * Subclass of Command class. Creates deadline task and executes it.
 */
public class DeadlineCommand extends Command {
    private String taskDetails;
    private String timeDetails;
    private String commandMessage = "";

    public DeadlineCommand(String[] details) throws KoraException {
        if (details.length != 2) {
            throw new KoraException("AiGu! Deadline needs to have a due date!");
        }
        taskDetails = details[0].replace("deadline ", "");
        timeDetails = details[1].replace("by ", "");
    }


    @Override
    public String getCommandMessage() {
        return commandMessage;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws KoraException {
        Task currentTask = new Deadline(taskDetails, timeDetails);
        taskList.addTask(currentTask);
        storage.saveTask(currentTask);
        commandMessage = "Okay! I have added this task" + "\n"
                + currentTask.toString() + "\n"
                + String.format("Now you have %d tasks!", taskList.getLength());
    }
}
