package sillybot.commands;

import java.io.IOException;
import java.util.Objects;

import sillybot.Storage;
import sillybot.Ui;
import sillybot.tasks.Task;
import sillybot.tasks.TaskList;

/**
 * Represents a MarkCommand object that handles the mark command.
 */
public class MarkCommand extends Command {
    private final int taskNumber;

    /**
     * Creates a MarkCommand object.
     *
     * @param taskNumber The index of the task to be marked as done.
     */
    public MarkCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the MarkCommand object.
     *
     * @return The String representation of the MarkCommand object.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        String response;

        try {
            Task task = tasks.get(taskNumber - 1);

            if (Objects.equals(task.getStatusIcon(), "X")) {
                response = ui.showAlreadyDone();
            } else {
                response = task.markTaskAsDone(task);
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
