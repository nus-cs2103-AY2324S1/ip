package minion.commands;

import java.io.IOException;

import minion.data.TaskList;
import minion.data.exception.IllegalValueException;
import minion.data.task.Task;
import minion.storage.Storage;
import minion.utils.StringFormatter;

/**
 * Represents a mark command.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    private final int taskIdx;

    /**
     * Constructs a mark command object.
     * @param taskIdx index of task in task list.
     */
    public MarkCommand(int taskIdx) {
        this.taskIdx = taskIdx;
    }

    /**
     * Executes the mark command.
     * @param tasks Task list.
     * @param storage Storage of chatbot.
     * @throws IllegalValueException if any argument(s) are invalid.
     * @throws IOException if there is IO error.
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) throws IllegalValueException, IOException {
        Task task = tasks.markTask(taskIdx);
        storage.writeToFile(tasks);
        return new CommandResult(
            StringFormatter.format(
                "Nice! I've marked this task as done:",
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
        if (o == null || !(o instanceof MarkCommand)) {
            return false;
        }
        MarkCommand c = (MarkCommand) o;
        return this.taskIdx == c.taskIdx;
    }
}
