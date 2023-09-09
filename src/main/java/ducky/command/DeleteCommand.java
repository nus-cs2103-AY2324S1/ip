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
     * @param ui       UserInterface of Ducky chatbot instance.
     * @param storage  Storage module of Ducky chatbot instance.
     * @return
     * @throws DuckyException If exceptions specific to Ducky are raised.
     */
    @Override
    public String execute(TaskList taskList, UserInterface ui, Storage storage) throws DuckyException {
        int deleteIndex = this.inputIndex - 1;
        Task deletedTask = taskList.deleteTask(deleteIndex);
        storage.save(taskList);
        ui.showMessagePerLine(
                "Okay! I've removed this task:",
                deletedTask.toString(),
                taskList.getListLengthStatus()
        );
        return null;
    }
}
