package duke.command;

/**
 * Lists all tasks in the list to the user.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public ListCommand() {
    }

    /**
     * Lists all current tasks in the list and sends it as a response to the user.
     *
     * @return the response for the user.
     */
    public String[] execute() {
        String[] response;
        int numTasks = this.duke.getTasks().size();
        if (numTasks == 0) {
            response = new String[1];
            response[0] = "There are no items in your list.";
        } else {
            response = new String[numTasks + 1];
            response[0] = "Here are the tasks in your list:";
            for (int i = 1; i <= numTasks; i++) {
                response[i] = (i + ". " + this.duke.getTasks().get(i));
            }
        }
        return response;
    }
}
