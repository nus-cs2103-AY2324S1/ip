/**
 * Representation of a command
 * to list all tasks in list.
 * 
 * @author Alvis Ng (supermii2)
 */
public class CommandTaskList extends Command {
    CommandTaskList(Rock client) {
        super(client);
    }
    @Override
    /**
     * Removes task from task list.
     * @param input Unused
     * @throws IllegalArgumentException Thrown when invalid index is given.
     */
    public void accept(Parser input) {
        this.client.ui.respond(this.client.taskList.toString());
    }
}
