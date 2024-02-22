package duke.commands;
import java.io.IOException;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.Task.Priority;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * The Command class is an abstract base class for various command types in the Duke application.
 * Each specific command type is implemented as a nested static class within this class.
 * Subclasses of Command define the execution behavior for their respective command types.
 */
public abstract class Command {

    /**
     * Executes the command with access to task data and storage by returning a string.
     *
     * @param tasks   The TaskList containing tasks to be manipulated.
     * @param storage The storage object for reading and writing task data.
     * @throws DukeException If an error occurs during command execution.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;

    /**
     * Returns the CommandType associated with the specific command type.
     *
     * @return The CommandType of the command.
     */
    public abstract CommandType getType();

    /**
     * The ExitCommand class represents the "bye" command in the Duke application.
     * Its execution allows the user to exit the application.
     * It saves the task data to storage before displaying the goodbye message.
     */
    public static class ExitCommand extends Command {

        /**
         * Executes the "bye" command, saving the task data to storage and displaying the goodbye message.
         *
         * @param tasks   The TaskList containing tasks to be saved.
         * @param storage The storage object for writing task data.
         * @throws DukeException If an error occurs during task data saving.
         */
        @Override
        public String execute(TaskList tasks, Storage storage) throws DukeException {
            try {
                storage.saveTasksToFile(tasks);
                return Ui.MESSAGE_GOODBYE;
            } catch (IOException e) {
                throw new DukeException("Error saving duke.tasks to file: " + e.getMessage());
            }
        }

        @Override
        public CommandType getType() {
            return CommandType.BYE;
        }
    }

    /**
     * The ListCommand class represents the "list" command in the Duke application, allowing the user to list all tasks.
     * It displays the list of tasks in the user interface.
     */
    public static class ListCommand extends Command {

        /**
         * Executes the "list" command, displaying the list of tasks in the user interface.
         *
         * @param tasks   The TaskList containing tasks to be displayed.
         * @param storage The storage object (not used in this command).
         * @throws DukeException If an error occurs during task listing.
         */
        @Override
        public String execute(TaskList tasks, Storage storage) throws DukeException {
            return tasks.list();
        }

        @Override
        public CommandType getType() {
            return CommandType.LIST;
        }
    }

    /**
     * The MarkConmand class represents the "mark" command in the Duke application.
     * Its execution allows the user to mark a task as done.
     */
    public static class MarkCommand extends Command {

        /** The number of the task to be marked as done. */
        private int taskNumber;

        /**
         * Constructs a Mark command with the specified task number.
         *
         * @param taskNumber The number of the task to be marked as done.
         */
        public MarkCommand(int taskNumber) {
            this.taskNumber = taskNumber;
        }

        /**
         * Executes the "mark" command, marking the specified task as done in the task list.
         *
         * @param tasks   The TaskList containing tasks to be manipulated.
         * @param storage The storage object (not used in this command).
         * @throws DukeException If an error occurs during task marking.
         */
        @Override
        public String execute(TaskList tasks, Storage storage) throws DukeException {
            int index = taskNumber - 1;
            return tasks.markTaskAsDone(index);
        }

        @Override
        public CommandType getType() {
            return CommandType.MARK;
        }
    }

    /**
     * The UnmarkCommand class represents the "unmark" command in the Duke application.
     * Its execution allows the user to unmark a task.
     */
    public static class UnmarkCommand extends Command {

        /** The number of the task to be unmarked. */
        private int taskNumber;

        /**
         * Constructs an Unmark command with the specified task number.
         *
         * @param taskNumber The number of the task to be unmarked.
         */
        public UnmarkCommand(int taskNumber) {
            this.taskNumber = taskNumber;
        }

        /**
         * Executes the "unmark" command, unmarking the specified task in the task list.
         *
         * @param tasks   The TaskList containing tasks to be manipulated.
         * @param storage The storage object (not used in this command).
         * @throws DukeException If an error occurs during task unmarking.
         */
        @Override
        public String execute(TaskList tasks, Storage storage) throws DukeException {
            int index = taskNumber - 1;
            return tasks.unmarkTask(index);
        }

        @Override
        public CommandType getType() {
            return CommandType.UNMARK;
        }
    }

    /**
     * The PriorityCommand class represents the "priority" command in the Duke application.
     * It sets the priority of the specified task in the task list.
     */
    public static class PriorityCommand extends Command {
        /** The number of the task to set priority. */
        private int taskNumber;

        /** The priority to set. */
        private Priority priority;

        /**
         * Constructs a Priority command with the specified task number and priority.
         *
         * @param taskNumber The number of the task to set priority.
         * @param priority   The priority to set.
         */
        public PriorityCommand(int taskNumber, Priority priority) {
            this.taskNumber = taskNumber;
            this.priority = priority;
        }

        /**
         * Executes the "priority" command, setting the priority of the specified task in the task list.
         *
         * @param tasks   The TaskList containing tasks to be manipulated.
         * @param storage The storage object (not used in this command).
         * @throws DukeException If an error occurs during task priority setting.
         */
        @Override
        public String execute(TaskList tasks, Storage storage) throws DukeException {
            int index = taskNumber - 1;
            return tasks.setPriority(index, priority);
        }

        @Override
        public CommandType getType() {
            return CommandType.PRIORITY;
        }
    }

    /**
     * The DeleteCommand class represents the "delete" command in the Duke application.
     * Its execution allows the user to delete a task.
     */
    public static class DeleteCommand extends Command {

        /** The number of the task to be deleted. */
        private int taskNumber;

        /**
         * Constructs a Delete command with the specified task number.
         *
         * @param taskNumber The number of the task to be deleted.
         */
        public DeleteCommand(int taskNumber) {
            this.taskNumber = taskNumber;
        }

        /**
         * Executes the "delete" command, deleting the specified task from the task list.
         *
         * @param tasks   The TaskList containing tasks to be manipulated.
         * @param storage The storage object (not used in this command).
         * @throws DukeException If an error occurs during task deletion.
         */
        @Override
        public String execute(TaskList tasks, Storage storage) throws DukeException {
            int index = taskNumber - 1;
            return tasks.deleteTask(index);
        }

        @Override
        public CommandType getType() {
            return CommandType.DELETE;
        }
    }

    /**
     * The AddCommand class represents commands that add new tasks in the Duke application.
     * This includes the "todo", "deadline" and "event" commands.
     * It adds the specified task to the task list.
     */
    public static class AddCommand extends Command {

        /** The task to be added. */
        private Task task;

        /** The type of the add command (e.g., ADD_TODO, ADD_DEADLINE, ADD_EVENT). */
        private CommandType commandType;

        /**
         * Constructs an Add command with the specified task and command type.
         *
         * @param task        The task to be added.
         * @param commandType The type of the add command (e.g., ADD_TODO, ADD_DEADLINE, ADD_EVENT).
         */
        public AddCommand(Task task, CommandType commandType) {
            this.task = task;
            this.commandType = commandType;
        }

        /**
         * Executes the "add" command, adding the specified task to the task list.
         *
         * @param tasks   The TaskList containing tasks to be manipulated.
         * @param storage The storage object (not used in this command).
         * @throws DukeException If an error occurs during task addition.
         */
        @Override
        public String execute(TaskList tasks, Storage storage) throws DukeException {
            return tasks.addTask(task);
        }

        @Override
        public CommandType getType() {
            return this.commandType;

        }
    }

    /**
     * The FindCommand class represents the "find" command in the Duke application, allowing the user to find tasks.
     * It finds tasks in the task list that contain the specified keyword.
     */
    public static class FindCommand extends Command {
        /** The keyword to be searched for. */
        private String keyword;

        /**
         * Constructs a Find command with the specified keyword.
         *
         * @param keyword The keyword to be searched for.
         */
        public FindCommand(String keyword) {
            this.keyword = keyword;
        }

        /**
         * Executes the "find" command, finding tasks in the task list that contain the specified keyword.
         *
         * @param tasks   The TaskList containing tasks to be manipulated.
         * @param storage The storage object (not used in this command).
         * @throws DukeException If an error occurs during task finding.
         */
        @Override
        public String execute(TaskList tasks, Storage storage) throws DukeException {
            return tasks.findTasks(keyword);
        }

        @Override
        public CommandType getType() {
            return CommandType.FIND;
        }
    }

    /**
     * The InvalidCommand class represents an invalid or unrecognized command in the Duke application.
     * It throws a DukeException with an error message.
     */
    public static class InvalidCommand extends Command {

        /** The error message describing the invalid command. */
        private String message;

        /**
         * Constructs an Invalid command with the specified error message.
         *
         * @param message The error message describing the invalid command.
         */
        public InvalidCommand(String message) {
            this.message = message;
        }

        /**
         * Executes the Invalid command, throwing a DukeException with the error message.
         *
         * @param tasks   The TaskList (not used in this command).
         * @param storage The storage object (not used in this command).
         * @throws DukeException Always throws a DukeException with the specified error message.
         */
        @Override
        public String execute(TaskList tasks, Storage storage) throws DukeException {
            throw new DukeException(message);
        }

        @Override
        public CommandType getType() {
            return CommandType.INVALID;
        }
    }
}
