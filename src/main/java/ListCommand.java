/**
 * Command that prints the tasks in a list.
 */
public class ListCommand implements Command{

    /**
     * Prints the task in a list.
     *
     * @param tasks The task list containing the tasks.
     * @param ui The user interface used to print the list.
     * @return {@code false} as the program should continue running.
     */
    @Override
    public  boolean execute(TaskList tasks,Ui ui){
        ui.respond(tasks.toString());
        return false;
    }
}
