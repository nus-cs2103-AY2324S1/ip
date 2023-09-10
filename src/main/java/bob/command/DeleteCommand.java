package bob.command;

import bob.exception.BobException;
import bob.exception.BobInvalidTaskNumberException;
import bob.storage.StorageFile;
import bob.task.Task;
import bob.task.TaskList;
import bob.ui.TextGenerator;

import java.util.ArrayList;

/**
 * The DeleteCommand class encapsulates logic that can be executed
 * to delete tasks from the current task list.
 */
public class DeleteCommand extends Command {
    private ArrayList<Integer> taskNumbers;
    private TaskList deletedTasks;

    /**
     * Constructor for the DeleteCommand Class.
     *
     * @param taskNumbers Task number of tasks to be deleted
     */
    public DeleteCommand(ArrayList<Integer> taskNumbers) {
        this.taskNumbers = taskNumbers;
        this.deletedTasks = new TaskList();
    }

    @Override
    public void execute(TaskList taskList, StorageFile storageFile) throws BobException {
        try {
            for (Integer i: taskNumbers) {
                taskList.getTask(i - 1);
            }
        } catch (BobInvalidTaskNumberException e) {
            String message = "One of the tasks you are trying to delete is non-existent!\n";
            String recommendation = "Use the command: \"list\" to find out what tasks you have.";
            throw new BobInvalidTaskNumberException(message + recommendation);
        }
        for (Integer i : taskNumbers) {
            Task currTask = taskList.getTask(i - 1);
            deletedTasks.addTask(currTask);
            taskList.deleteTask(i);
        }
        storageFile.saveTasks(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getOutputMessage() throws BobException {
        return TextGenerator.getDeleteMultipleMessage(deletedTasks);
    }
}
