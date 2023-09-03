package Handlers;

import Models.TaskArray;

import static Printers.BasicOutputPrinter.printBasicOutput;
import static java.lang.System.exit;

/**
 * ExitHandler handles all exit commands (i.e. bye, exit).
 */
public class ExitHandler implements Command {

    private TaskArray tasks;

    /**
     * Constructor for ExitHandler.
     *
     * @param tasks The TaskArray we are working with
     */
    public ExitHandler(TaskArray tasks) {
        this.tasks = tasks;
    }

    /**
     * Parses the content of the input.
     *
     * @param commandContent The content of the input.
     */
    @Override
    public void parseCommandContent(String commandContent) {
        printBasicOutput("Bye. Hope to see you again soon!");

        exit(0);
    }
}
