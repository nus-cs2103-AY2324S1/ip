package duke.utility;

import duke.exception.EmptyTaskException;
import duke.exception.InvalidDeadlineException;
import duke.exception.InvalidEventException;
import duke.exception.InvalidTaskException;
import duke.exception.MissingTimeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Command class encapsulates all commands users can input into Bobi
 * and the results from each of the commands.
 *
 * @author ruo-x
 */
public class Command {
    /** Storage that stores all tasks from Bobi into backend */
    private final Storage storage;

    /** Ui that determines which system message to display to the user */
    private final Ui ui;

    /** TaskList stores all tasks that the user has input */
    private final TaskList taskList;

    /**
     * Constructor for a Command object.
     *
     * @param storage Current storage system.
     * @param ui Current Ui system.
     * @param taskList Current task list.
     */
    public Command(Storage storage, Ui ui, TaskList taskList) {
        this.storage = storage;
        this.ui = ui;
        this.taskList = taskList;
    }

    /**
     * Breaks down user's inputs to identify what is
     * the name and status of task and create a To-Do object.
     * Prints out related error message if user did not input the task name.
     * Adds newly created To-Do object into the current task list.
     * Saves newly created To-Do object into the current storage.
     * Returns a system message to inform user of successful execution.
     *
     * @param input User's input from the keyboard.
     * @return A system message to inform user of successful execution or
     * error message to inform user of failed execution
     */
    // Add to do task
    public String handleToDo(String input) {
        try {
            ToDo task = Parser.parseTodo(input);

            // Add new To Do into task list
            this.taskList.addTask(task);

            // Save new task into backend
            this.storage.saveTask(task);

            // Return system message to inform action
            return this.ui.printAddTask(task);
        } catch (EmptyTaskException e) {
            return Ui.printError(e);
        }
    }

    /**
     * Breaks down user's inputs to identify what is
     * the name, status, and deadline of task and create a Deadline object.
     * Prints out related error message if user did not input the task name or deadline.
     * Adds newly created Deadline object into the current task list.
     * Saves newly created Deadline object into the current storage.
     * Returns a system message to inform user of successful execution.
     *
     * @param input User's input from the keyboard.
     * @return A system message to inform user of successful execution or
     * error message to inform user of failed execution
     */
    // Add deadline task
    public String handleDeadline(String input) {
        try {
            Deadline task = Parser.parseDeadline(input);

            // Add new Deadline into task list
            this.taskList.addTask(task);

            // Save new task into backend
            this.storage.saveTask(task);

            // Return system message to inform action
            return this.ui.printAddTask(task);
        } catch (MissingTimeException | EmptyTaskException | InvalidDeadlineException e) {
            return Ui.printError(e);
        }
    }

    /**
     * Breaks down user's inputs to identify what is the name, status,
     * start date and time, end date and time of task and create an Event object.
     * Returns related error message if user did not input the task name, start/end date and time.
     * Adds newly created Event object into the current task list.
     * Saves newly created Event object into the current storage.
     * Returns a system message to inform user of successful execution.
     *
     * @param input User's input from the keyboard.
     * @return A system message to inform user of successful execution or
     * error message to inform user of failed execution
     */
    public String handleEvent(String input) {
        try {
            Event task = Parser.parseEvent(input);

            // Add new Event into task list
            this.taskList.addTask(task);

            // Save new task into backend
            this.storage.saveTask(task);

            // Return system message to inform action
            return this.ui.printAddTask(task);
        } catch (MissingTimeException | EmptyTaskException | InvalidEventException e) {
            return Ui.printError(e);
        }
    }

    /**
     * Breaks down user's inputs into the task number to mark as done.
     * Returns the relevant error message if user did not input a valid task number.
     * Updates task from the task list as done.
     * Updates task from the storage as done.
     * Returns a system message to inform user of successful execution.
     *
     * @param input User's input from the keyboard.
     * @return A system message to inform user of successful execution.
     */
    public String handleMark(String input) {
        try {
            int taskNumber = Parser.parseActions(input);
            Task task = this.taskList.getTask(taskNumber);

            // Update backend, important to update backend first!
            this.storage.updateTask(task, 1);

            // Mark task as done
            task.mark();

            // Return system message to inform action
            return Ui.printUpdateTask(task);
        } catch (EmptyTaskException | InvalidTaskException e) {
            return Ui.printError(e);
        }
    }

    /**
     * Breaks down user's inputs into the task number to mark as un-done.
     * Returns the relevant error message if user did not input a valid task number.
     * Updates task from the task list as un-done.
     * Updates task from the storage as un-done.
     * Returns a system message to inform user of successful execution.
     *
     * @param input User's input from the keyboard.
     * @return A system message to inform user of successful execution.
     */
    public String handleUnMark(String input) {
        try {
            int taskNumber = Parser.parseActions(input);
            Task task = this.taskList.getTask(taskNumber);

            // Update backend
            this.storage.updateTask(task, 0);

            // un-mark task
            task.unMark();

            // Return system message to inform action
            return Ui.printUpdateTask(task);
        } catch (EmptyTaskException | InvalidTaskException e) {
            return Ui.printError(e);
        }
    }

    /**
     * Breaks down user's inputs into the task number to delete task.
     * Returns the relevant error message if user did not input a valid task number.
     * Delete task from the task list.
     * Delete task from the storage.
     * Returns a system message to inform user of successful execution.
     *
     * @param input User's input from the keyboard.
     * @return A system message to inform user of successful execution.
     */
    public String handleDelete(String input) {
        try {
            // Get task from input
            int taskNumber = Parser.parseActions(input);
            Task task = this.taskList.getTask(taskNumber);

            // Delete task from backend
            this.storage.deleteTask(task);

            // Delete task from task list
            this.taskList.deleteTask(task);

            // Return system message to inform action
            return this.ui.printDeleteTask(task);
        } catch (EmptyTaskException | InvalidTaskException e) {
            return Ui.printError(e);
        }
    }
}
