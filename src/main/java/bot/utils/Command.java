package bot.utils;

import java.util.regex.Pattern;

import bot.enums.DoneStatus;
import bot.exceptions.EmptyListException;
import bot.exceptions.InvalidArgumentException;
import bot.exceptions.InvalidIndexException;
import bot.exceptions.InvalidTaskException;

/**
 * Command abstraction for executing commands.
 */
public abstract class Command {
    /**
     * Regex pattern for delete commands.
     */
    private static final Pattern PATTERN_DELETE = Pattern.compile("delete -?\\d+");
    /**
     * Regex pattern for mark commands.
     */
    private static final Pattern PATTERN_MARK = Pattern.compile("mark -?\\d+");
    /**
     * Regex pattern for unmark commands.
     */
    private static final Pattern PATTERN_UNMARK = Pattern.compile("unmark -?\\d+");

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
     * @param str Full command string.
     * @return MarkCommand object.
     * @throws InvalidArgumentException If the string doesn't contain an int.
     */
    public static Command mark(String str) throws InvalidArgumentException {
        if (!PATTERN_MARK.matcher(str).matches()) {
            throw new InvalidArgumentException();
        }
        int index = Integer.parseInt(str.substring(5));
        return new MarkCommand(index, DoneStatus.DONE);
    }

    /**
     * Creates a MarkCommand to instruct the bot to unmark the task at the given index
     * in the list.
     *
     * @param str Full command string.
     * @return MarkCommand object.
     * @throws InvalidArgumentException If the string doesn't contain an int.
     */
    public static Command unmark(String str) throws InvalidArgumentException {
        if (!PATTERN_UNMARK.matcher(str).matches()) {
            throw new InvalidArgumentException();
        }
        int index = Integer.parseInt(str.substring(7));
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
     * Creates a MarkCommand to instruct the bot to delete the task at the given index
     * in the list.
     *
     * @param str Full command string.
     * @return DeleteCommand object.
     * @throws InvalidArgumentException If the string doesn't contain an int.
     */
    public static Command delete(String str) throws InvalidArgumentException {
        if (!PATTERN_DELETE.matcher(str).matches()) {
            throw new InvalidArgumentException();
        }
        int index = Integer.parseInt(str.substring(7));
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
    public abstract boolean isExit();

    /**
     * Executes the command.
     *
     * @param tasks   Task list containing tasks.
     * @param ui      User interface for interacting with users.
     * @param storage Storage for storing data.
     * @throws EmptyListException    If an illegal operation is performed on an empty list.
     * @throws InvalidIndexException If the command tries to access an invalid index.
     * @throws InvalidTaskException  If the command creates a task and fails to do so.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws EmptyListException,
            InvalidIndexException, InvalidTaskException;

    /**
     * Bot.Command to indicate the end of the program.
     */
    private static class ExitCommand extends Command {

        /**
         * Checks if the bot should exit after the execution of the command.
         *
         * @return True if the bot should exit, false otherwise.
         */
        public boolean isExit() {
            return true;
        }

        /**
         * Executes the command.
         *
         * @param tasks   Bot.Task list containing tasks.
         * @param ui      User interface for interacting with users.
         * @param storage Bot.Storage for storing data.
         */
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            ui.showGoodbye();
        }
    }

    /**
     * Bot.Command to list all tasks in the task list.
     */
    private static class ListCommand extends Command {
        /**
         * Checks if the bot should exit after the execution of the command.
         *
         * @return True if the bot should exit, false otherwise.
         */
        public boolean isExit() {
            return false;
        }

        /**
         * Executes the command.
         *
         * @param tasks   Bot.Task list containing tasks.
         * @param ui      User interface for interacting with users.
         * @param storage Bot.Storage for storing data.
         * @throws EmptyListException If an illegal operation is performed on an empty list.
         */
        public void execute(TaskList tasks, Ui ui, Storage storage) throws EmptyListException {
            if (tasks.size() == 0) {
                throw new EmptyListException();
            }
            ui.println("Here are the tasks in your list:");
            ui.displayTaskList(tasks);
        }
    }

    /**
     * Bot.Command to mark the task as done or not done.
     */
    private static class MarkCommand extends Command {
        /**
         * Index to mark the task at.
         */
        private int index;
        /**
         * Mark the task as done or not done.
         */
        private DoneStatus done;

        /**
         * Creates a MarkCommand with the command to mark or unmark the task
         * at the given index.
         *
         * @param index Index to mark the task at.
         * @param done  Mark the task as done or not done.
         */
        protected MarkCommand(int index, DoneStatus done) {
            this.index = index;
            this.done = done;
        }

        /**
         * Checks if the bot should exit after the execution of the command.
         *
         * @return True if the bot should exit, false otherwise.
         */
        public boolean isExit() {
            return false;
        }

        /**
         * Executes the command.
         *
         * @param tasks   Bot.Task list containing tasks.
         * @param ui      User interface for interacting with users.
         * @param storage Bot.Storage for storing data.
         * @throws InvalidIndexException If the command tries to access an invalid index.
         */
        public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException {
            if (done.equals(DoneStatus.DONE)) {
                tasks.mark(index);
                ui.println("I'll mark this as done:\n" + tasks.get(index).toString());
            } else {
                tasks.unmark(index);
                ui.println("I'll mark this as not done:\n" + tasks.get(index).toString());
            }
        }
    }

    /**
     * Bot.Command to add tasks to the task list.
     */
    private static class AddCommand extends Command {
        /**
         * Full command string
         */
        private String input;

        /**
         * Creates an AddCommand with the full command string.
         *
         * @param input Full command string.
         */
        protected AddCommand(String input) {
            this.input = input;
        }

        /**
         * Checks if the bot should exit after the execution of the command.
         *
         * @return True if the bot should exit, false otherwise.
         */
        public boolean isExit() {
            return false;
        }

        /**
         * Executes the command.
         *
         * @param tasks   Bot.Task list containing tasks.
         * @param ui      User interface for interacting with users.
         * @param storage Bot.Storage for storing data.
         * @throws InvalidTaskException If the command creates a task and fails to do so.
         */
        public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidTaskException {
            Task newTask = Task.makeTask(input);
            ui.println("Added:\n" + newTask.toString());
            tasks.add(newTask);
            ui.println("Now you have " + tasks.size() + " task(s) in the list.");
        }
    }

    /**
     * Command to delete tasks.
     */
    private static class DeleteCommand extends Command {
        /**
         * Index to delete task at.
         */
        private int index;

        /**
         * Creates a DeleteCommand with the given index.
         *
         * @param index Index to delete task at.
         */
        protected DeleteCommand(int index) {
            this.index = index;
        }

        /**
         * Checks if the bot should exit after the execution of the command.
         *
         * @return True if the bot should exit, false otherwise.
         */
        public boolean isExit() {
            return false;
        }

        /**
         * Executes the command.
         *
         * @param tasks   Bot.Task list containing tasks.
         * @param ui      User interface for interacting with users.
         * @param storage Bot.Storage for storing data.
         * @throws InvalidIndexException If the command tries to access an invalid index.
         */
        public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException {
            Task task = tasks.remove(index);
            System.out.println("I've removed this task:\n" + task.toString());
            System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
        }
    }

    /**
     * Command for finding specific tasks in the task list.
     */
    private static class FindCommand extends Command {
        /**
         * Full command string.
         */
        private String input;

        /**
         * Creates an FindCommand with the full command string.
         *
         * @param input Full command string.
         */
        protected FindCommand(String input) {
            this.input = input;
        }

        /**
         * Checks if the bot should exit after the execution of the command.
         *
         * @return True if the bot should exit, false otherwise.
         */
        public boolean isExit() {
            return false;
        }

        /**
         * Executes the command.
         *
         * @param tasks   Task list containing tasks.
         * @param ui      User interface for interacting with users.
         * @param storage Storage for storing data.
         */
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            TaskList queries = tasks.findAll(input.substring(5).trim());
            if (queries.size() > 0) {
                ui.println("Here are the matching tasks in your list:");
                ui.displayTaskList(queries);
            } else {
                ui.println("Sorry, no matching tasks found.");
            }
        }
    }
}
