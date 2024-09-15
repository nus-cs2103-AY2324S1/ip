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
     * @return Bot response when marking a task.
     */
    public static String execute(String input, TaskList tasks, Storage storage) {
        try {
            boolean isEmptyDescription = input.length() <= 5 || input.substring(5).isBlank();
            if (isEmptyDescription) {
                throw new EmptyDescriptionException();
            }
            Task task = new ToDo(input.substring(5));
            tasks.add(task);
            storage.rewrite(tasks.fileList());
            return Ui.addTask(task.toString(), tasks.size());
        } catch (EmptyDescriptionException e) {
            return Ui.emptyDesc("todo");
        }
    }
}
