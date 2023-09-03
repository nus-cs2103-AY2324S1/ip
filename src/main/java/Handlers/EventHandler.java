package Handlers;

import Exceptions.DukeInvalidFormatException;
import Models.Event;
import Models.TaskArray;

import static Printers.BasicOutputPrinter.printBasicOutput;
import static Printers.ErrorOutputPrinter.printErrorOutput;

/**
 * EventHandler handles all 'event' commands.
 */
public class EventHandler implements Command {

    private TaskArray tasks;

    /**
     * Constructor for EventHandler.
     *
     * @param tasks The TaskArray we are working with
     */
    public EventHandler(TaskArray tasks) {
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
            printErrorOutput("You cannot add an empty 'Event' task!");
        } else {
            try {
                String[] taskArr = commandContent.split("/", 3);

                if (taskArr.length != 3) {
                    String errorStr = "\nFormat for Event task incorrect!\n" +
                            "Expected 3 parts (Task name, start & end time) in input, got " + taskArr.length;
                    throw new DukeInvalidFormatException(errorStr);
                }

                tasks.addTask(new Event(taskArr[0].strip(), false, taskArr[1].strip(), taskArr[2].strip()));

                String output = "Got it, I've added this task: \n" +
                        tasks.get(tasks.size() - 1) + "\n" +
                        "You now have " + tasks.size() + " tasks in the list.";

                printBasicOutput(output);

            } catch (DukeInvalidFormatException e) {
                String errorString = "Something went wrong! Please format the task properly and add it again. \n\n" +
                        "Error: " + e;

                printErrorOutput(errorString);
            }
        }
    }
}
