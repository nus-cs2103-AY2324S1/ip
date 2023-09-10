package bob.command;

import bob.exception.BobException;
import bob.storage.StorageFile;
import bob.task.Task;
import bob.task.TaskList;
import bob.ui.TextGenerator;

import java.util.ArrayList;

/**
 * The DeleteCommand class encapsulates logic that can be executed
 * to delete multiple tasks from the current list of tasks.
 */
public class DeleteMultipleCommand extends Command {

    private ArrayList<Integer> taskNumbers;
    private TaskList deletedTasks;

    /**
     * Constructor for the DeleteMultipleCommand Class.
     *
     * @param taskNumbers Task number of tasks to be deleted
     */
    public DeleteMultipleCommand(ArrayList<Integer> taskNumbers) {
        this.taskNumbers = taskNumbers;
        this.deletedTasks = new TaskList();
    }

    @Override
    public void execute(TaskList taskList, StorageFile storageFile) throws BobException {
        //Guard Clause?
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
