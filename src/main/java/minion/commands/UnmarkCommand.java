package minion.commands;

import minion.data.exception.IllegalValueException;
import minion.data.TaskList;
import minion.data.task.Task;
import minion.storage.Storage;
import minion.ui.Ui;

import java.io.IOException;

public class UnmarkCommand extends Command {
    /**
     * Command word of unmark.
     */
    public static final String COMMAND_WORD = "unmark";
    /**
     * Index of task to unmark.
     */
    private final int taskIdx;

    /**
     * Constructs an unmarkCommand object.
     * @param taskIdx index of task.
     */
    public UnmarkCommand(int taskIdx) {
        this.taskIdx = taskIdx;
    }

    /**
     * Marks the task as undone.
     * @param tasks Task list.
     * @param ui Ui of chatbot.
     * @param storage Storage of chatbot.
     * @throws IllegalValueException when taskIdx is invalid.
     * @throws IOException when failed write to storage.
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

    @Override
    public boolean equals(Object o) {
        if(o == null || !(o instanceof UnmarkCommand)) return false;
        UnmarkCommand c = (UnmarkCommand) o;
        return this.taskIdx == c.taskIdx;
    }
}
