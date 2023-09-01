package minion.commands;

import java.io.IOException;

import minion.data.TaskList;
import minion.data.exception.IllegalValueException;
import minion.data.task.Task;
import minion.storage.Storage;
import minion.ui.Ui;

/**
 * Represents an unmark command.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    private final int taskIdx;

    /**
     * Constructs an unmark command object.
     * @param taskIdx index of task in task list.
     */
    public UnmarkCommand(int taskIdx) {
        this.taskIdx = taskIdx;
    }

    /**
     * Executes the unmark command.
     * @param tasks Task list.
     * @param ui Ui of chatbot.
     * @param storage Storage of chatbot.
     * @throws IllegalValueException if any argument(s) are invalid.
     * @throws IOException if there is IO error.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws
            IllegalValueException, IOException {
        Task task = tasks.unmarkTask(taskIdx);
        ui.print("OK, I've marked this task as not done yet:",
                "\t" + task.toString()
        );
        storage.writeToFile(tasks);
    }

    /**
     * Checks whether this object equals the other object.
     * @param o other object.
     * @return whether this object equals the other object.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof UnmarkCommand)) {
            return false;
        }
        UnmarkCommand c = (UnmarkCommand) o;
        return this.taskIdx == c.taskIdx;
    }
}
