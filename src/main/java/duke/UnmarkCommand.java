package duke;

/**
 * The UnmarkCommand class represents the command to unmark a task.
 */
public class UnmarkCommand {

    /**
     * Runs the UnmarkCommand.
     *
     * @param input Input typed by user.
     * @param tasks List of tasks.
     * @param storage Stores the file and handles file methods.
     */
    public static String execute(String input, TaskList tasks, Storage storage) {
        try {
            int number = Integer.parseInt(input.substring(7));
            if (number > tasks.size() || number <= 0) {
                throw new InvalidTaskException();
            }
            Task task = tasks.get(number - 1);
            task.setMark(false);
            storage.rewrite(tasks.fileList());
            return Ui.unmark(task);
        } catch (InvalidTaskException e) {
            return Ui.invalidTask();
        }
    }
}
