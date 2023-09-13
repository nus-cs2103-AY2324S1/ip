package duke.command;

import java.util.List;
import java.util.stream.Collectors;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Searches through the task list and displays tasks matching the keyword.
 */
public class FindCommand implements Command {
    private final String taskDetail;

    /**
     * Constructs a FindCommand with the specified keyword details.
     *
     * @param detail The keyword to search for in the task names.
     */
    public FindCommand(String detail) {
        this.taskDetail = detail;
    }

    /**
     * Executes the command by searching and displaying tasks containing the keyword.
     * Displays the matching tasks to the user.
     *
     * @param tasks   The list of tasks to search through.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder output = new StringBuilder("Here are the matching tasks in your list:");
        List<Task> filteredTasks = tasks.stream()
                .filter(task -> task.getName().contains(this.taskDetail))
                .collect(Collectors.toList());

        if (filteredTasks.size() == 0) {
            ui.sendMessage("Sorry, there is no matching tasks in your list.");
        } else {
            for (int i = 0; i < filteredTasks.size(); i++) {
                output.append("\n").append(i + 1).append(".").append(tasks.get(i).toString());
            }
            ui.sendMessage(output.toString());
        }
    }

    /**
     * Does nothing.
     *
     * @param tasks The list of tasks.
     */
    @Override
    public void loadTask(TaskList tasks) {
        //Do nothing
    }

    /**
     * Does nothing.
     *
     * @param tasks The list of tasks.
     * @return The command to be executed.
     * @throws DukeException If an error occurs during command execution.
     */
    @Override
    public Command undoTask(TaskList tasks) throws DukeException {
        return new EmptyCommand();
    }
}
