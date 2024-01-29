package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeBadInputException;
import duke.task.Task;
import duke.task.TodoTask;

/**
 * Represents the Todo command
 */
public class TodoCommand extends Command {

    /**
     * Stores the description of the task
     */
    private String desc;

    /**
     * Constructs a todo command
     *
     * @param desc - the desc of the command
     */
    public TodoCommand(String desc) {
        this.desc = desc;
    }

    /**
     * Encapsulates the execution logic of the command
     *
     * @param taskList - the task list instance of the current duke
     * @param ui       - the ui instance of DUKE
     * @param storage  - the storage instance to allow the command to write to the
     *                 storage
     * @return the reply of Quack
     * @throws DukeBadInputException - if the input cannot be used
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeBadInputException {
        // Add task to storage and task list, throws error if not successful
        Task newTask = new TodoTask(desc);
        taskList.add(newTask);
        if (!storage.writeToFile(newTask.getStored())) {
            return ui.getUnexpectedErrorMessage("unable to write to storage");
        }
        return ui.getNewTaskMessage(newTask, taskList.length());
    }

    /**
     * Checks if the command is the exit command
     *
     * @return true if it is the exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Checks if it is the exact same command
     *
     * @param other the other command in question
     * @return true if there are equals
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof TodoCommand) {
            TodoCommand command = (TodoCommand) other;
            return this.desc.equals(command.desc);
        }
        return false;
    }
}
