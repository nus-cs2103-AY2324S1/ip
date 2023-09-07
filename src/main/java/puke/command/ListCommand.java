package puke.command;

import puke.managers.TaskList;

/**
 * A Command class that when executed, prints the string representation of the task list.
 */
public class ListCommand extends Command {

    public ListCommand(String rest) {
        super(false, rest.isEmpty());
    }

    /**
     * Executes the command by printing out the corresponding message.
     * If the command is in the wrong format.
     *
     * @param tl The task list.
     * @return The message String.
     */
    public String execute(TaskList tl) {
        if (!super.isValid) {
            return ERROR_MESSAGE;
        } else {
            return generateMessage(tl);
        }
    }
    private String generateMessage(TaskList tl) {
        return "Here is the collection of items, previously designated to be known as Tasks, "
                + "that you have inputted over a previous unspecified period of time "
                + "that may or may not require urgent attention, but will nevertheless necessitate "
                + "some level of action within an either "
                + "indicated or non indicated time period.\n\n"
                + tl.printOut();
    }
    /**
     * Returns a boolean indicating if the other object is an instance of ListCommand.
     *
     * @param other Another object
     * @return a boolean
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof ListCommand);
    }
}
