package Evaluator;

import Logger.Logger;
import Storage.FileStorage;
import TaskList.TaskList;
import TaskList.Deadline;
import Exception.KevinException;

import java.util.ArrayList;

public class DeadlineStrategy extends BaseStrategy {
    DeadlineStrategy(TaskList taskList, ArrayList<String> arguments) {
        super(taskList, arguments);
    }

    @Override
    public boolean evaluate(Logger logger, FileStorage fileStorage, boolean isInFile) throws KevinException {
        Boolean isDone = Boolean.getBoolean(this.arguments.get(0));
        String name = this.arguments.get(1);
        String deadline = this.arguments.get(2);

        Deadline newDeadline = taskList.addDeadline(isDone, name, deadline);

        if (!isInFile) {
            fileStorage.addDeadline(newDeadline);
            logger.log("Got it. I've added this task: \n\t\t" + newDeadline +
                    "\n\tNow you have " + taskList.size() + " tasks in the list.");
        }
        return true;
    }
}
