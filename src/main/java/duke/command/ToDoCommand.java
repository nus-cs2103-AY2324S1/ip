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

    /**
     * Class constructor of ToDoCommand.
     * @param details Task description.
     * @throws KoraException When there is no description.
     */
    public ToDoCommand(String[] details) throws KoraException {
        taskDetails = details[0].replace(details[0].split(" ")[0], "").replace(" ", "");
        if (taskDetails.equals("")) {
            throw new KoraException("AiGu! ToDo must have details!");
        }
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public String getCommandMessage() {
        return commandMessage;
    }

    /**
     * {@inheritDoc}
     *
     * Creates ToDo task.
     * @param taskList List with tasks.
     * @param storage Storage where tasks are stored.
     * @throws KoraException When there is error creating ToDo task.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) throws KoraException {
        Task currentTask = new ToDo(taskDetails);
        taskList.addTask(currentTask);
        storage.saveTask(currentTask);
        commandMessage = "Okay! I have added this task" + "\n"
                + currentTask.toString() + "\n" + String.format("Now you have %d tasks!", taskList.getLength());
    }
}
