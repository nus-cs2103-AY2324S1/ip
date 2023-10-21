package command;

import duke.Storage;
import duke.TaskList;

import exceptions.DukeException;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int taskToDeleteIndex;

    /**
     * Constructs a DeleteCommand object with the task index to delete.
     *
     * @param response String containing the task index.
     */
    public DeleteCommand(int response) {
        super(false);
        this.taskToDeleteIndex = response;
    }

    /**
     * Executes the delete command, removing the task from the task list and updating storage.
     *
     * @param taskList The task list to operate on.
     * @param storage  The storage handler for reading/writing tasks.
     * @throws DukeException If there is an error executing the command.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        try {
            String taskRemoved = taskList.getTaskInString(taskToDeleteIndex);
            taskList.deleteTask(taskToDeleteIndex);
            storage.writeListToFile(taskList);
            String s = String.format("Okay master! I have removed the following task from the list:\n %s\n %s",
                    taskRemoved, taskList.NumberOfTaskListInString());
            return s;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("This task does not exist! Try again!");
        }

    }
}
