/**
 * Representation of a command
 * to list all tasks in list.
 * 
 * @author Alvis Ng (supermii2)
 */
public class CommandTaskList extends Command {
    @Override
    /**
     * Removes task from task list.
     * @param input Unused
     * @throws IllegalArgumentException Thrown when invalid index is given.
     */
    public void accept(Parser input) {
        Rock.respond(Rock.taskList.toString());
    }
}
