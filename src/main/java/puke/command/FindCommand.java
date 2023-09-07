package puke.command;

import puke.managers.TaskList;

/**
 * A Command class that when executed, looks for tasks in the task list that match the keywords provided.
 */
public class FindCommand extends Command {
    private final String key;

    /**
     * Creates a new FindCommand
     * @param rest the rest of the input line.
     */
    public FindCommand(String rest) {
        super(false, true);
        this.key = rest;
    }

    /**
     * Executes the command, printing out the corresponding message from the UI while printing each matching
     * task in the task list.
     * @param tl the task list
     * @return the message String.
     */
    public String execute(TaskList tl) {
        try {
            return "As per the instructions provided, I shall initiate a search into your list of items, of which "
                    + "we have previously declared to be known as tasks due too their relatively "
                    + "urgent need of attention within a specified or unspecified frame of time, for those of which "
                    + "have an alphabetical similarity to the frame of reference that you have provided.\n\n"
                    + tl.find(key);
        } catch (Exception PukeException) {
            return ERROR_MESSAGE;
        }
    }
}
