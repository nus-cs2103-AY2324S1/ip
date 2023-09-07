package LogicHandlers.CommandHandlers;

import Models.TaskArray;

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
    public String parseCommandContent(String commandContent) {
        return ("Not a valid command!");
    }
}
