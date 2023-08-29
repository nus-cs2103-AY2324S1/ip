package Evaluator;

import Logger.Logger;
import TaskList.TaskList;
import java.util.ArrayList;

public class ToDoStrategy extends BaseStrategy {
    public ToDoStrategy(TaskList taskList, ArrayList<String> arguments) {
        super(taskList, arguments);
    }

    @Override
    public boolean evaluate(Logger logger) {
        String toDoName = this.arguments.get(0);

        logger.log(taskList.addToDo(toDoName));
        return true;
    }
}
