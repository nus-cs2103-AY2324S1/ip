package kevin.evaluator;

import kevin.ui.Logger;
import kevin.storage.FileStorage;
import kevin.taskList.TaskList;
import kevin.exception.KevinException;

import java.util.ArrayList;

public abstract class BaseStrategy {
    protected TaskList taskList;
    protected ArrayList<String> arguments;

    public BaseStrategy(TaskList taskList, ArrayList<String> arguments) {
        this.taskList = taskList;
        this.arguments = arguments;
    }

    public boolean evaluate(Logger logger, FileStorage fileStorage, boolean isInFile) throws KevinException {
        return false;
    }
}
