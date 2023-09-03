package LogicHandlers.CommandHandlers;

import Exceptions.DukeInvalidDateTimeException;
import Exceptions.DukeInvalidFormatException;
import Models.Deadline;
import Models.TaskArray;

import static LogicHandlers.Parsers.DateTimeParser.parseDateTimeFromString;
import static Ui.BasicOutputPrinter.printBasicOutput;
import static Ui.ErrorOutputPrinter.printErrorOutput;

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
    public void parseCommandContent(String commandContent) {
        if (commandContent.equals("")) {
            printErrorOutput("You cannot add an empty 'Deadline' task!");
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

                String output = "Got it, I've added this task: \n" +
                        tasks.get(tasks.size() - 1) + "\n" +
                        "You now have " + tasks.size() + " tasks in the list";

                printBasicOutput(output);

            } catch (DukeInvalidFormatException | DukeInvalidDateTimeException e) {
                String errorString = "Something went wrong! Please format the task properly and add it again. \n" +
                        "Error: " + e;

                printErrorOutput(errorString);
            }
        }
    }
}
