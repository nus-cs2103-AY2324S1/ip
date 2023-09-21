package duke.command;

import duke.data.storage.Store;

/**
 * Represents a command that interpret the user input string and list all tasks in the task list.
 */
public class ListCommand implements Command {

    /**
     * Lists all tasks in the task list.
     * @param input String input from user, which should be "list".
     */
    @Override
    public String execute(String input) {
        Store s = Store.getInstance();
        if (s.getTaskCount() == 0) {
            return "You have no tasks in your list!";
        }
        return "Here are the tasks in your list:\n" + s;
    }
}
