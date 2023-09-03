package LogicHandlers.CommandHandlers;

import Models.TaskArray;

import static Printers.ErrorOutputPrinter.printErrorOutput;

/**
 * InvalidInputHandler handles all 'todo' commands.
 */
public class InvalidInputHandler implements Command{
    private TaskArray tasks;

    /**
     * Constructor for InvalidInputHandler.
     *
     * @param tasks The TaskArray we are working with
     */
    public InvalidInputHandler(TaskArray tasks) {
        this.tasks = tasks;
    }

    /**
     * Replies to the invalid command.
     * commandContent is redundant as command is invalid.
     *
     * @param commandContent The content of the input.
     */
    @Override
    public void parseCommandContent(String commandContent) {
        printErrorOutput("Not a valid command!");
    }
}
