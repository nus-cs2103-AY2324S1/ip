package Evaluator;

import Logger.Logger;
import TaskList.TaskList;

import java.util.ArrayList;

public class EventStrategy extends BaseStrategy {
    EventStrategy(TaskList taskList, ArrayList<String> arguments) {
        super(taskList, arguments);
    }

    @Override
    public boolean evaluate(Logger logger) {
        String name = this.arguments.get(0);
        String startTime = this.arguments.get(1);
        String endTime = this.arguments.get(2);

        logger.log(taskList.addEvent(name, startTime, endTime));
        return true;
    }
}
