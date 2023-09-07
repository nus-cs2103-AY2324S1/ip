package LogicHandlers.CommandHandlers;

import Models.TaskArray;

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
    public String parseCommandContent(String commandContent) {
        if (this.tasks.isEmpty()) {
            return ("You have no tasks!");

        }

        return ("Your tasks: \n" + this.tasks);
    }
}
