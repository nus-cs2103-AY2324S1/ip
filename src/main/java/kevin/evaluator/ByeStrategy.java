package kevin.evaluator;

import java.util.ArrayList;

import kevin.exception.KevinException;
import kevin.storage.FileStorage;
import kevin.tasklist.TaskList;
import kevin.ui.Logger;

/**
 * A class responsible for the logic for BYE command.
 */
public class ByeStrategy extends BaseStrategy {
    /**
     * Constructor to initialize ByeStrategy.
     * @param taskList This is the TaskList where the tasks are stored and operations are defined.
     * @param arguments This is an ArrayList where all the needed arguments are stored.
     */
    public ByeStrategy(TaskList taskList, ArrayList<String> arguments) {
        super(taskList, arguments);
    }

    /**
     * Handles the logic of the command.
     * @param logger This is the Logger that handles System.out.println.
     * @param fileStorage This is the FileStorage that handles the storage in the local computer.
     * @param isInFile This is the boolean to show whether the task is in the local computer's file.
     * @return Returns a boolean that determines the continuation of the evaluation.
     * @throws KevinException On the detection of errors.
     */
    public String evaluate(Logger logger, FileStorage fileStorage, boolean isInFile) throws KevinException {
        return "bye";
    }
}
