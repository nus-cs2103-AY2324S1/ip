package kevin.evaluator;

import kevin.ui.Logger;
import kevin.storage.FileStorage;
import kevin.taskList.TaskList;
import kevin.taskList.Task;
import kevin.exception.KevinException;

import java.util.ArrayList;

/**
 * A class responsible for the logic for DELETE command.
 */
public class DeleteStrategy extends BaseStrategy {
    /**
     * Constructor to initialize DeleteStrategy.
     * @param taskList This is the TaskList where the tasks are stored and operations are defined.
     * @param arguments This is an ArrayList where all the needed arguments are stored.
     */
    public DeleteStrategy(TaskList taskList, ArrayList<String> arguments) {
        super(taskList, arguments);
    }

    /**
     * Handles the logic of the DELETE command.
     * @param logger This is the Logger that handles System.out.println.
     * @param fileStorage This is the FileStorage that handles the storage in the local computer.
     * @param isInFile This is the boolean to show whether the task is in the local computer's file.
     * @return Returns a boolean that determines the continuation of the evaluation.
     * @throws KevinException On the detection of errors.
     */
    @Override
    public boolean evaluate(Logger logger, FileStorage fileStorage, boolean isInFile) throws KevinException {
        int index = Integer.parseInt(this.arguments.get(1));

        Task deletedTask = this.taskList.delete(index);

        if (!isInFile) {
            fileStorage.deleteTask(index);
            logger.log("Noted. I've removed this task: \n\t\t" +
                    deletedTask +
                    "\n\tNow you have " + this.taskList.size() + " tasks in the list.");
        }

        return true;
    }
}
