package kevin.evaluator;

import kevin.ui.Logger;
import kevin.storage.FileStorage;
import kevin.taskList.TaskList;
import kevin.exception.KevinException;

import java.util.ArrayList;

/**
 * Abstract class responsible for the logic for different commands.
 */
public abstract class BaseStrategy {
    protected TaskList taskList;
    protected ArrayList<String> arguments;

    /**
     * Constructor to initialize BaseStrategy.
     * @param taskList This is the TaskList where the tasks are stored and operations are defined.
     * @param arguments This is an ArrayList where all the needed arguments are stored.
     */
    public BaseStrategy(TaskList taskList, ArrayList<String> arguments) {
        this.taskList = taskList;
        this.arguments = arguments;
    }

    /**
     * Handles the logic of the command.
     * @param logger This is the Logger that handles System.out.println.
     * @param fileStorage This is the FileStorage that handles the storage in the local computer.
     * @param isInFile This is the boolean to show whether the task is in the local computer's file.
     * @return Returns a boolean that determines the continuation of the evaluation.
     * @throws KevinException On the detection of errors.
     */
    public boolean evaluate(Logger logger, FileStorage fileStorage, boolean isInFile) throws KevinException {
        return false;
    }
}
