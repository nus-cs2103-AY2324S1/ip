package duke;

/**
 * The ToDoCommand class represents the command to create a todo task.
 */
public class ToDoCommand {

    /**
     * Runs the ToDoCommand.
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
            Task task = new ToDo(input.substring(5));
            tasks.add(task);
            storage.rewrite(tasks.fileList());
            Ui.addTask(task.toString(), tasks.size());
        } catch (EmptyDescriptionException e) {
            Ui.emptyDesc("todo");
        }
    }
}
