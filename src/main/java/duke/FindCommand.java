package duke;

/**
 * The FindCommand class represents the command to find a task.
 */
public class FindCommand {

    /**
     * Runs the FindCommand.
     *
     * @param input Input typed by user.
     * @param tasks List of tasks.
     * @param storage Stores the file and handles file methods.
     */
    public static void execute(String input, TaskList tasks, Storage storage) {
        try {
            if (input.length() <= 5 || input.substring(5).isBlank()) {
                throw new EmptyDescriptionException();
            }
        } catch (EmptyDescriptionException e) {
            Ui.emptyDesc("find");
        }
        Ui.searchTasks(tasks.showSpecificTasks(input.substring(5)));
    }
}
