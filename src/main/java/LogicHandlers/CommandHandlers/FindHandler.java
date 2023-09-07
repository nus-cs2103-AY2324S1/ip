package LogicHandlers.CommandHandlers;

import Models.TaskArray;

/**
 * FindHandler handles all find commands.
 */
public class FindHandler implements Command {

    private TaskArray tasks;

    /**
     * Constructor for FindHandler.
     *
     * @param tasks The TaskArray we are working with
     */
    public FindHandler(TaskArray tasks) {
        this.tasks = tasks;
    }

    /**
     * Parses the content of the input.
     *
     * @param commandContent The content of the input.
     */
    @Override
    public String parseCommandContent(String commandContent) {
        return this.tasks.findTasks(commandContent);
    }
}
