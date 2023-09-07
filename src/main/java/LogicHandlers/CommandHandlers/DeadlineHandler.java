package LogicHandlers.CommandHandlers;

import Exceptions.DukeInvalidDateTimeException;
import Exceptions.DukeInvalidFormatException;
import Models.Deadline;
import Models.TaskArray;

import static LogicHandlers.Parsers.DateTimeParser.parseDateTimeFromString;

/**
 * DeadlineHandler handles all 'deadline' commands.
 */
public class DeadlineHandler implements Command {
    private TaskArray tasks;

    /**
     * Constructor for DeadlineHandler.
     *
     * @param tasks The TaskArray we are working with
     */
    public DeadlineHandler(TaskArray tasks) {
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
            return ("You cannot add an empty 'Deadline' task!");
        } else {
            try {
                String[] taskArr = commandContent.split("/", 2);

                if (taskArr.length != 2) {
                    String errorStr = "\nFormat for Deadline task incorrect!\n" +
                            "Expected 2 parts (Task name & deadline) in input, got " + taskArr.length;
                    throw new DukeInvalidFormatException(errorStr);
                }

                tasks.addTask(new Deadline(taskArr[0].strip(),
                        false, parseDateTimeFromString(taskArr[1].strip())));

                return ("Got it, I've added this task: \n" +
                        tasks.get(tasks.size() - 1) + "\n" +
                        "You now have " + tasks.size() + " tasks in the list");

            } catch (DukeInvalidFormatException | DukeInvalidDateTimeException e) {
                return ("Something went wrong! Please format the task properly and add it again. \n" +
                        "Error: " + e);
            }
        }
    }
}
