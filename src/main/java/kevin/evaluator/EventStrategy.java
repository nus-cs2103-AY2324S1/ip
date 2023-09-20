package kevin.evaluator;

import java.util.ArrayList;

import kevin.exception.KevinException;
import kevin.storage.FileStorage;
import kevin.tasklist.Event;
import kevin.tasklist.TaskList;
import kevin.ui.Logger;



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
    public String evaluate(Logger logger, FileStorage fileStorage, boolean isInFile) throws KevinException {
        Boolean isDone = Boolean.parseBoolean(this.arguments.get(0));
        String name = this.arguments.get(1);
        String startTime = this.arguments.get(2);
        String endTime = this.arguments.get(3);

        Event newEvent = taskList.addEvent(isDone, name, startTime, endTime);

        if (!isInFile) {
            fileStorage.addEvent(newEvent);
            return logger.log(String.format("Got it. I've added this task: \n%s\nNow you have %d tasks in the list.",
                    newEvent, taskList.size()));
        }

        return "";
    }
}
