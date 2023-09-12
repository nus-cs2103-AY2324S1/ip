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
     * @return Bot response when finding a task.
     */
    public static String execute(String input, TaskList tasks, Storage storage) {
        try {
            boolean isEmptyDescription = input.length() <= 5 || input.substring(5).isBlank();
            if (isEmptyDescription) {
                throw new EmptyDescriptionException();
            }
            return Ui.searchTasks(tasks.showSpecificTasks(input.substring(5)));
        } catch (EmptyDescriptionException e) {
            return Ui.emptyDesc("find");
        }
    }
}
