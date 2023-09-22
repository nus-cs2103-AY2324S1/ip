package logic.commandlogic;

import models.TaskArray;
import models.ToDo;

/**
 * TodoHandler handles all 'todo' commands.
 */
public class TodoHandler implements Command{
    private TaskArray tasks;

    /**
     * Constructor for TodoHandler.
     *
     * @param tasks The TaskArray we are working with
     */
    public TodoHandler(TaskArray tasks) {
        this.tasks = tasks;
    }

    /**
     * Parses the content of the input.
     *
     * @param commandContent The content of the input.
     */
    @Override
    public String parseCommandContent(String commandContent) {
        try {
            assert(!commandContent.equals("")): "You cannot add an empty todo task!";

            tasks.addTask(new ToDo(commandContent, false));

            return ("Got it, I've added this task: \n" +
                    tasks.get(tasks.size() - 1) + "\n" +
                    "You now have " + tasks.size() + " tasks in the list.");

        } catch(AssertionError e) {
            return ("Something went wrong! Please format the task properly and add it again. \n" +
                    "Error: " + e);
        }
    }
}
