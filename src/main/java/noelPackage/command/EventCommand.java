package noelPackage.command;

import noelPackage.helper.Storage;
import noelPackage.helper.Tasklist;

/**
 * Represents a command to add a new event.
 * This command, when executed, will parse the user input and
 * create a new event task, which is added to the task list.
 */
public class EventCommand extends Command {

    private final String command;

    /**
     * Initializes a new instance of the EventCommand class.
     *
     * @param command The entire command string input by the user.
     */
    public EventCommand(String command) {
        this.command = command;
    }

    /**
     * Executes the event command.
     * <p>
     * This method will parse the command string, create a new event task, and
     * add it to the task list. If the command string is invalid, it will return
     * an error message.
     * </p>
     *
     * @param tasks   The list of tasks the user has.
     * @param storage The storage helper responsible for saving and loading tasks.
     * @return A string representing the result of executing the command.
     */
    @Override
    public String execute(Tasklist tasks, Storage storage) {

        assert command != null : "Should have an input!";
        String[] commandInputs = this.command.split("event ");

        if (commandInputs.length == 1) {
            System.out.println("OOPS!!! The description of a event cannot be empty.");
        } else {
            String[] eventsHelper = commandInputs[1].split(" /from ");

            if (eventsHelper.length == 2) {
                eventsHelper = eventsHelper[1].split(" /to ");
                if (eventsHelper.length == 2) {
                    String returnStr = tasks.addEvent(eventsHelper[0], eventsHelper[0], eventsHelper[1]);
                    storage.writeToFile(tasks.getTaskAsList());
                    return returnStr;
                } else {
                    return ("Insufficient commands provided!");
                }
            } else {
                return ("Insufficient commands provided!");
            }
        }
        return null;
    }
}
