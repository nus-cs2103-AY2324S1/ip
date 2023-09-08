package duke;

/**
 * The ListCommand class represents the command to show the list of tasks.
 */
public class ListCommand {

    /**
     * Runs the ListCommand.
     *
     * @param input Input typed by user.
     * @param tasks List of tasks.
     * @param storage Stores the file and handles file methods.
     */
    public static String execute(String input, TaskList tasks, Storage storage) {
        return Ui.listOfTasks(tasks.showList());
    }
}
