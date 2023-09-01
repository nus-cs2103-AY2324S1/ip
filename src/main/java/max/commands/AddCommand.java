package max.commands;

import max.commands.*;
import max.exception.MaxException;
import max.parser.Parser;
import max.storage.Storage;
import max.tasks.TaskList;
import max.tasks.*;
import max.ui.Ui;

/**
 * Represents command to add tasks.
 */
public class AddCommand extends Command {
    public static final String COMMAND_WORD_DEADLINE = "deadline";
    public static final String COMMAND_WORD_EVENT = "event";
    public static final String COMMAND_WORD_TODO = "todo";
    private Task task;

    /**
     * Specifies task to be added.
     * @param task Task to be added
     */
    public AddCommand(Task task) {
        this.task = task;
    }
    /**
     * Executes add command. Adds task to storage, saves task list to memory, and prints success message.
     *
     * @param tasks Task list
     * @param ui UI
     * @param storage Storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        storage.writeToFile(tasks);
        ui.showAdd(task, tasks.getList().size());
    }
    /**
     * Checks if command is an exit command.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
