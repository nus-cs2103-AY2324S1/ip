package Evaluator;

import Logger.Logger;
import TaskList.TaskList;

import java.util.ArrayList;

public abstract class BaseStrategy {
    protected TaskList taskList;
    protected ArrayList<String> arguments;

    public BaseStrategy(TaskList taskList, ArrayList<String> arguments) {
        this.taskList = taskList;
        this.arguments = arguments;
    }

    public boolean evaluate(Logger logger) {
        return false;
    }
}
