package sillybot.commands;

import java.io.IOException;
import java.util.Objects;

import sillybot.Storage;
import sillybot.Ui;
import sillybot.tasks.Task;
import sillybot.tasks.TaskList;

/**
 * Represents a UnmarkCommand object that handles the unmark command.
 */
public class UnmarkCommand extends Command {
    private final int taskNumber;

    /**
     * Creates a UnmarkCommand object.
     *
     * @param taskNumber The task number of the task to be unmarked.
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the UnmarkCommand object.
     *
     * @return The String representation of the UnmarkCommand object.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        String response;

        try {
            Task task = tasks.get(taskNumber - 1);

            if (Objects.equals(task.getStatusIcon(), " ")) {
                response = ui.showAlreadyUndone();
            } else {
                response = task.unmarkTaskAsUndone(task);
            }

            storage.save(tasks);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            response = ui.showInvalidIndex();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return response;
    }
}
