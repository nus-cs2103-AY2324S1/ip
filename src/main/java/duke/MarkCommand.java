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
     * @return Bot response when marking a task.
     */
    public static String execute(String input, TaskList tasks, Storage storage) {
        try {
            int number = Integer.parseInt(input.substring(5));
            boolean isInvalidTask = number > tasks.size() || number <= 0;
            if (isInvalidTask) {
                throw new InvalidTaskException();
            }
            Task task = tasks.get(number - 1);
            task.setMark(true);
            storage.rewrite(tasks.fileList());
            return Ui.mark(task);
        } catch (InvalidTaskException e) {
            return Ui.invalidTask();
        }
    }
}
