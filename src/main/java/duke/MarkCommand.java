package duke;

/**
 * The MarkCommand class represents the command to mark a task.
 */
public class MarkCommand {

    /**
     * Runs the MarkCommand.
     *
     * @param input Input typed by user.
     * @param tasks List of tasks.
     * @param storage Stores the file and handles file methods.
     */
    public static void execute(String input, TaskList tasks, Storage storage) {
        try {
            int number = Integer.parseInt(input.substring(5));
            if (number > tasks.size() || number <= 0) {
                throw new InvalidTaskException();
            }
            Task task = tasks.get(number - 1);
            task.setMark(true);
            storage.rewrite(tasks.fileList());
            Ui.mark(task);
        } catch (InvalidTaskException e) {
            Ui.invalidTask();
        }
    }
}
