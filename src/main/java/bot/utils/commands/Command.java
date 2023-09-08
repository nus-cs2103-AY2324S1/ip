package bot.utils.commands;

import bot.enums.DoneStatus;
import bot.exceptions.EmptyListException;
import bot.exceptions.InvalidIndexException;
import bot.exceptions.InvalidTaskException;
import bot.utils.Storage;
import bot.utils.TaskList;
import bot.utils.Ui;

/**
 * Command abstraction for executing commands.
 */
public abstract class Command {
    /**
     * Default constructor. Does nothing.
     */
    public Command() {}

    /**
     * Creates an ExitCommand to indicate the end of the program.
     *
     * @return ExitCommand object.
     */
    public static Command exit() {
        return new ExitCommand();
    }

    /**
     * Creates a ListCommand to instruct the bot to display the items in the list.
     *
     * @return ListCommand object.
     */
    public static Command list() {
        return new ListCommand();
    }

    /**
     * Creates a MarkCommand to instruct the bot to mark the task at the given index
     * in the list.
     *
     * @param index Mark index.
     * @return MarkCommand object.
     */
    public static Command mark(int index) {
        return new MarkCommand(index, DoneStatus.DONE);
    }

    /**
     * Creates a MarkCommand to instruct the bot to unmark the task at the given index
     * in the list.
     *
     * @param index Unmark index.
     * @return MarkCommand object.
     */
    public static Command unmark(int index) {
        return new MarkCommand(index, DoneStatus.NOT_DONE);
    }

    /**
     * Creates an AddCommand to instruct the bot to add a task to the list.
     *
     * @param str Full command string.
     * @return AddCommand object.
     */
    public static Command add(String str) {
        return new AddCommand(str);
    }

    /**
     * Creates a DeleteCommand to instruct the bot to delete the task at the given index
     * in the list.
     *
     * @param index Index to delete.
     * @return DeleteCommand object.
     */
    public static Command delete(int index) {
        return new DeleteCommand(index);
    }

    /**
     * Creates a FindCommand to instruct the bot to look for tasks that meet specific criteria.
     *
     * @param str Full command string.
     * @return FindCommand object.
     */
    public static Command find(String str) {
        return new FindCommand(str);
    }

    /**
     * Checks if the bot should exit after the execution of the command.
     *
     * @return True if the bot should exit, false otherwise.
     */
    public abstract boolean getExitStatus();

    /**
     * Executes the command.
     *
     * @param tasks   Task list containing tasks.
     * @param ui      User interface for interacting with users.
     * @param storage Storage for storing data.
     * @return Bot's response to the command.
     * @throws EmptyListException    If an illegal operation is performed on an empty list.
     * @throws InvalidIndexException If the command tries to access an invalid index.
     * @throws InvalidTaskException  If the command adds a task and fails to do so.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws EmptyListException,
            InvalidIndexException, InvalidTaskException;

}
