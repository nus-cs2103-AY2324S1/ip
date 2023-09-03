package Handlers;

import Models.TaskArray;

import static Printers.BasicOutputPrinter.printBasicOutput;
import static java.lang.System.exit;

/**
 * ListTasksHandler handles all list commands.
 */
public class ListTasksHandler implements Command{
    private TaskArray tasks;

    /**
     * Constructor for ListTasksHandler.
     *
     * @param tasks The TaskArray we are working with
     */
    public ListTasksHandler(TaskArray tasks) {
        this.tasks = tasks;
    }

    /**
     * Parses the content of the input.
     *
     * @param commandContent The content of the input.
     */
    @Override
    public void parseCommandContent(String commandContent) {
        if (this.tasks.isEmpty()) {
            printBasicOutput("You have no tasks!");

        }

        String output = "Your tasks: \n" + this.tasks;
        printBasicOutput(output);
    }
}
