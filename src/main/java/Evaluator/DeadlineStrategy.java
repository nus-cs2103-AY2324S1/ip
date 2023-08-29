package Evaluator;

import Logger.Logger;
import TaskList.TaskList;

import java.util.ArrayList;

public class DeadlineStrategy extends BaseStrategy {
    DeadlineStrategy(TaskList taskList, ArrayList<String> arguments) {
        super(taskList, arguments);
    }

    @Override
    public boolean evaluate(Logger logger) {
        String name = this.arguments.get(0);
        String deadline = this.arguments.get(1);

        logger.log(taskList.addDeadline(name, deadline));
        return true;
    }
}
