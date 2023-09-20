package duke.command;

import duke.exception.KoraException;
import duke.list.TaskList;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Subclass of Command class. Creates todo task and executes it.
 */
public class ToDoCommand extends Command {
    private String taskDetails;
    private String commandMessage = "";
    public ToDoCommand(String[] details) throws KoraException {
        taskDetails = details[0].replace(details[0].split(" ")[0], "").replace(" ", "");
        if (taskDetails.equals("")) {
            throw new KoraException("AiGu! ToDo must have details!");
        }
    }

    @Override
    public String getCommandMessage() {
        return commandMessage;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws KoraException {
        Task currentTask = new ToDo(taskDetails);
        taskList.addTask(currentTask);
        storage.saveTask(currentTask);
        commandMessage = "Okay! I have added this task" + "\n"
                + currentTask.toString() + "\n" + String.format("Now you have %d tasks!", taskList.getLength());
    }
}
