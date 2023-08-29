package Evaluator;

import Logger.Logger;
import TaskList.TaskList;
import java.util.ArrayList;

public class MarkStrategy extends BaseStrategy {
    public MarkStrategy(TaskList taskList, ArrayList<String> arguments) {
        super(taskList, arguments);
    }

    @Override
    public boolean evaluate(Logger logger) {
        int index = Integer.parseInt(this.arguments.get(0));

        logger.log(this.taskList.mark(index));
        return true;
    }
}
