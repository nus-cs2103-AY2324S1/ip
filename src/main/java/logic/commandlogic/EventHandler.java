package logic.commandlogic;

import exceptions.DukeInvalidDateTimeException;
import exceptions.DukeInvalidFormatException;
import models.Event;
import models.TaskArray;

import static logic.parsers.DateTimeParser.parseDateTimeFromString;

/**
 * EventHandler handles all 'event' commands.
 */
public class EventHandler implements Command {

    private TaskArray tasks;

    /**
     * Constructor for EventHandler.
     *
     * @param tasks The TaskArray we are working with
     */
    public EventHandler(TaskArray tasks) {
        this.tasks = tasks;
    }

    /**
     * Parses the content of the input.
     *
     * @param commandContent The content of the input.
     */
    @Override
    public String parseCommandContent(String commandContent) {

        try {
            assert(!commandContent.equals("")): "You cannot add an empty event task!";

            String[] taskArr = commandContent.split("/", 3);

            if (taskArr.length != 3) {
                String errorStr = "\nFormat for Event task incorrect!\n" +
                        "Expected 3 parts (Task name, start & end time) in input, got " + taskArr.length;
                throw new DukeInvalidFormatException(errorStr);
            }

            tasks.addTask(new Event(taskArr[0].strip(), false,
                    parseDateTimeFromString(taskArr[1].strip()), parseDateTimeFromString(taskArr[2].strip())));

            return ("Got it, I've added this task: \n" +
                    tasks.get(tasks.size() - 1) + "\n" +
                    "You now have " + tasks.size() + " tasks in the list.");

        } catch (DukeInvalidFormatException | DukeInvalidDateTimeException | AssertionError e) {
            return ("Something went wrong! Please format the task properly and add it again. \n\n" +
                    "Error: " + e);
        }
    }
}
