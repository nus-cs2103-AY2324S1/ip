package LogicHandlers.CommandHandlers;

import Exceptions.DukeInvalidDateTimeException;
import Exceptions.DukeInvalidFormatException;
import Models.Event;
import Models.TaskArray;

import static LogicHandlers.Parsers.DateTimeParser.parseDateTimeFromString;

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
        if (commandContent.equals("")) {
            return ("You cannot add an empty 'Event' task!");
        } else {
            try {
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

            } catch (DukeInvalidFormatException | DukeInvalidDateTimeException e) {
                return ("Something went wrong! Please format the task properly and add it again. \n\n" +
                        "Error: " + e);
            }
        }
    }
}
