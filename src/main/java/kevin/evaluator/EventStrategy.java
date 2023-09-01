package kevin.evaluator;

import kevin.ui.Logger;
import kevin.storage.FileStorage;
import kevin.taskList.TaskList;
import kevin.taskList.Event;
import kevin.exception.KevinException;

import java.util.ArrayList;

/**
 * A class responsible for the logic for EVENT command.
 */
public class EventStrategy extends BaseStrategy {
    /**
     * Constructor to initialize EventStrategy.
     * @param taskList This is the TaskList where the tasks are stored and operations are defined.
     * @param arguments This is an ArrayList where all the needed arguments are stored.
     */
    EventStrategy(TaskList taskList, ArrayList<String> arguments) {
        super(taskList, arguments);
    }

    /**
     * Handles the logic of the EVENT command.
     * @param logger This is the Logger that handles System.out.println.
     * @param fileStorage This is the FileStorage that handles the storage in the local computer.
     * @param isInFile This is the boolean to show whether the task is in the local computer's file.
     * @return Returns a boolean that determines the continuation of the evaluation.
     * @throws KevinException On the detection of errors.
     */
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
