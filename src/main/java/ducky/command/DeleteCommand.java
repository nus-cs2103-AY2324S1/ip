package ducky.command;

import ducky.DuckyException;
import ducky.Storage;
import ducky.TaskList;
import ducky.task.Task;

/**
 * Represents a command that deletes a task from Ducky's task list.
 */
public class DeleteCommand extends Command {

    private final int inputIndex;

    /**
     * Constructs a command that deletes task with the specified index from Ducky's task list.
     * @param inputIndex Index of task in task list to be deleted.
     */
    public DeleteCommand(int inputIndex) {
        this.inputIndex = inputIndex;
    }

    /**
     * Deletes the task with the given index from task list, saves state to file system,
     * then reflects changes to user interface.
     *
     * @param taskList TaskList of Ducky chatbot instance.
     * @param storage  Storage module of Ducky chatbot instance.
     * @return String confirming the operation and the deleted task.
     * @throws DuckyException If exceptions specific to Ducky are raised.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DuckyException {
        int deleteIndex = this.inputIndex - 1;
        Task deletedTask = taskList.deleteTask(deleteIndex);
        storage.save(taskList);
        return String.format("%s\n%s\n%s\n",
                "Okay! I've added this task:",
                deletedTask,
                taskList.getListLengthStatus());
    }
}
