package kevin.evaluator;

import kevin.exception.KevinException;
import kevin.storage.FileStorage;
import kevin.taskList.Task;
import kevin.taskList.TaskList;
import kevin.ui.Logger;

import java.util.ArrayList;

/**
 * A class responsible for the logic for FIND command.
 */
public class FindStrategy extends BaseStrategy {
    /**
     * Constructor to initialize FindStrategy.
     * @param taskList This is the TaskList where the tasks are stored and operations are defined.
     * @param arguments This is an ArrayList where all the needed arguments are stored.
     */
    public FindStrategy(TaskList taskList, ArrayList<String> arguments) {
        super(taskList, arguments);
    }

    /**
     * Handles the logic of the FIND command.
     * @param logger This is the Logger that handles System.out.println.
     * @param fileStorage This is the FileStorage that handles the storage in the local computer.
     * @param isInFile This is the boolean to show whether the task is in the local computer's file.
     * @return Returns a boolean that determines the continuation of the evaluation.
     * @throws KevinException On the detection of errors.
     */
    @Override
    public boolean evaluate(Logger logger, FileStorage fileStorage, boolean isInFile) throws KevinException {
        String keyword = this.arguments.get(1);
        logger.log(taskList.find(keyword));
        return true;
    }
}
