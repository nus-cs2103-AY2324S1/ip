package Evaluator;

import Ui.Logger;
import Storage.FileStorage;
import TaskList.TaskList;
import Exception.KevinException;

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
