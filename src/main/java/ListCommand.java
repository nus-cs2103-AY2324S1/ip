/**
 * The ListCommand class represents a command to list tasks from a TaskList.
 * When executed, it displays the list of tasks to the user through the UI.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand.
     */
    public ListCommand() {
    }

    /**
     * Executes the ListCommand to list tasks from a TaskList and display them through the UI.
     *
     * @param tasksList The TaskList containing the tasks to be listed.
     * @param ui The user interface for displaying messages to the user.
     * @param storage The storage component for interacting with task data storage.
     */
    @Override
    public void execute(TaskList tasksList, Ui ui, Storage storage) {
        if (tasksList.isEmpty()) {
            ui.showText("Here are the tasks in your list:");
            ui.showNewLine();
        } else {
            ui.showText("Here are the tasks in your list:");
            for (int i = 0; i < tasksList.tasksListLength(); i++) {
                if (i == tasksList.tasksListLength() - 1) {
                    ui.showText(i + 1 + "." + tasksList.get(i).toString());
                    ui.showNewLine();
                } else {
                    ui.showText(i + 1 + "." + tasksList.get(i).toString());
                }
            }
        }
    }
}
