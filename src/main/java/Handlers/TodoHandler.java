package Handlers;

import Models.TaskArray;
import Models.ToDo;

import static Printers.BasicOutputPrinter.printBasicOutput;
import static Printers.ErrorOutputPrinter.printErrorOutput;

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
    public void parseCommandContent(String commandContent) {
        if (commandContent.equals("")) {
            printErrorOutput("You cannot add an empty 'ToDo' task!");

        } else {
            tasks.addTask(new ToDo(commandContent, false));

            String output = "Got it, I've added this task: \n" +
                    tasks.get(tasks.size() - 1) + "\n" +
                    "You now have " + tasks.size() + " tasks in the list.";

            printBasicOutput(output);
        }
    }
}
