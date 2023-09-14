package alice.command;

import alice.exception.DukeException;
import alice.storage.Storage;
import alice.task.TaskList;
import alice.ui.Ui;

/**
 * Represents a command issued by the user to tag a task.
 */
public class TagCommand extends Command {
    private final int taskIndex; // The index of the task to be tagged.
    private final String[] tags; // The tags to be added to the task.

    /**
     * Constructs a TagCommand with the given index.
     *
     * @param taskIndex The index (0-based) of the task to be tagged.
     */
    public TagCommand(int taskIndex, String... tags) {
        this.taskIndex = taskIndex;
        this.tags = tags;
    }

    /**
     * Constructs a TagCommand with the given argument.
     *
     * @param argument The argument of the command.
     * @throws DukeException If the argument is invalid.
     */
    public TagCommand(String argument) throws DukeException {
        try {
            String[] inputs = argument.split(" ", 2);
            this.taskIndex = Integer.parseInt(inputs[0]) - 1;
            this.tags = inputs[1].split(" ");
        } catch (NumberFormatException e) {
            throw new DukeException(INDEX_NOT_NUMBER_ERROR_MESSAGE);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.tag(this.taskIndex, this.tags);
        storage.save(tasks.toFileString());
        return ui.showTagTask(tasks.get(this.taskIndex));
    }
}
