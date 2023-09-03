package minion.commands;

import java.io.IOException;

import minion.data.TaskList;
import minion.data.exception.IllegalValueException;
import minion.data.task.Task;
import minion.storage.Storage;
import minion.utils.StringFormatter;

/**
 * Represents a delete command.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private final int taskIdx;

    /**
     * Constructs a delete command.
     * @param taskIdx Index of task in task list.
     */
    public DeleteCommand(int taskIdx) {
        this.taskIdx = taskIdx;
    }

    /**
     * Executes the delete command.
     * @param tasks Task list.
     * @param storage Storage of chatbot.
     * @throws IllegalValueException if argument(s) are invalid.
     * @throws IOException if there is IO error.
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) throws IllegalValueException, IOException {
        Task task = tasks.deleteTask(taskIdx);
        storage.writeToFile(tasks);
        return new CommandResult(
            StringFormatter.format(
                "OK, I've removed this task",
                "\t" + task.toString()
            )
        );
    }

    /**
     * Checks whether this object equals the other object.
     * @param o other object.
     * @return whether this object equals the other object.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof DeleteCommand)) {
            return false;
        }
        DeleteCommand c = (DeleteCommand) o;
        return this.taskIdx == c.taskIdx;
    }
}
