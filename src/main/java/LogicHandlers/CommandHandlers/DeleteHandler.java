package LogicHandlers.CommandHandlers;

import Exceptions.DukeInvalidIndexException;
import Models.TaskArray;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Printers.BasicOutputPrinter.printBasicOutput;
import static Printers.ErrorOutputPrinter.printErrorOutput;

/**
 * DeleteHandler handles all 'delete' commands.
 */
public class DeleteHandler implements Command{
    private TaskArray tasks;

    /**
     * Constructor for DeleteHandler.
     *
     * @param tasks The TaskArray we are working with
     */
    public DeleteHandler(TaskArray tasks) {
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
            printErrorOutput("Please specify a task to delete!");
        } else {
            try {
                // Regex to tell if content is an integer
                Pattern intPattern = Pattern.compile("^\\d+$");
                Matcher matcher = intPattern.matcher(commandContent);

                if (!matcher.matches()) {
                    throw new DukeInvalidIndexException("Invalid task index! Please try again.");
                }

                int index = Integer.parseInt(commandContent) - 1;

                if (index >= tasks.size()) {
                    printErrorOutput("Task " + (index + 1) + " not found!");
                } else {
                    String output = "Noted, I've removed this task: \n" +
                            tasks.get(index) + "\n" +
                            "You now have " + (tasks.size() - 1) + " tasks in the list.";

                    printBasicOutput(output);

                    tasks.deleteTask(index);
                }
            } catch (DukeInvalidIndexException e) {
                printErrorOutput(e.toString());
            }
        }
    }
}
