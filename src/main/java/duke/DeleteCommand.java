package duke;

/**
 * The DeleteCommand class represents the command to delete a task.
 */
public class DeleteCommand {

    /**
     * Runs the DeleteCommand.
     *
     * @param input Input typed by user.
     * @param tasks List of tasks.
     * @param storage Stores the file and handles file methods.
     */
    public static void execute(String input, TaskList tasks, Storage storage) {
        try {
            int number = Integer.parseInt(input.substring(7));
            if (number > tasks.size() || number <= 0) {
                throw new InvalidTaskException();
            }
            Task task = tasks.delete(number - 1);
            storage.rewrite(tasks.fileList());
            Ui.delete(task, tasks.size());
        } catch (InvalidTaskException e) {
            Ui.invalidTask();
        }
    }
}
