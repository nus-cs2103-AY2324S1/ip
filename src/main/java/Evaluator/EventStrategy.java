package Evaluator;

import Ui.Logger;
import Storage.FileStorage;
import TaskList.TaskList;
import TaskList.Event;
import Exception.KevinException;

import java.util.ArrayList;

public class EventStrategy extends BaseStrategy {
    EventStrategy(TaskList taskList, ArrayList<String> arguments) {
        super(taskList, arguments);
    }


    @Override
    public boolean evaluate(Logger logger, FileStorage fileStorage, boolean isInFile) throws KevinException {
        Boolean isDone = Boolean.getBoolean(this.arguments.get(0));
        String name = this.arguments.get(1);
        String startTime = this.arguments.get(2);
        String endTime = this.arguments.get(3);

        Event newEvent = taskList.addEvent(isDone, name, startTime, endTime);

        if (!isInFile) {
            fileStorage.addEvent(newEvent);
            logger.log("Got it. I've added this task: \n\t\t" + newEvent +
                    "\n\tNow you have " + taskList.size() + " tasks in the list.");
        }

        return true;
    }
}
