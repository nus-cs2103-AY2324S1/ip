package Evaluator;

import Logger.Logger;
import TaskList.TaskList;
import java.util.ArrayList;

public class ListStrategy extends BaseStrategy {
    public ListStrategy(TaskList taskList, ArrayList<String> arguments) {
        super(taskList, arguments);
    }

    @Override
    public boolean evaluate(Logger logger) {
        logger.log(taskList.list());
        return true;
    }
}
