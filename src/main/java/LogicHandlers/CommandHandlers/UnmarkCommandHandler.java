package LogicHandlers.CommandHandlers;

import Exceptions.DukeInvalidIndexException;
import Models.TaskArray;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Printers.BasicOutputPrinter.printBasicOutput;
import static Printers.ErrorOutputPrinter.printErrorOutput;

/**
 * UnmarkCommandHandler handles all mark commands.
 */
public class UnmarkCommandHandler implements Command{
    private TaskArray tasks;

    /**
     * Constructor for UnmarkCommandHandler.
     *
     * @param tasks The TaskArray we are working with
     */
    public UnmarkCommandHandler(TaskArray tasks) {
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
            printErrorOutput("You cannot unmark an empty task!");

        } else {
            int index;

            try {
                // Regex to tell if content is an integer
                Pattern intPattern = Pattern.compile("^\\d+$");
                Matcher matcher = intPattern.matcher(commandContent);

                if (!matcher.matches()) {
                    throw new DukeInvalidIndexException("Invalid task index! Please try again.");
                }

                index = Integer.parseInt(commandContent) - 1;

                if (index >= tasks.size() || index < 0) {
                    printErrorOutput("Task " + (index + 1) + " not found!");
                } else {
                    tasks.unmarkTask(index);

                    String output = "OK, I've marked this task as not done yet: \n" +
                            tasks.get(index);

                    printBasicOutput(output);
                }
            } catch (DukeInvalidIndexException e) {
                printErrorOutput(e.toString());
            }
        }
    }
}


