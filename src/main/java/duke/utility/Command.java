package duke.utility;

import duke.exception.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class Command {
    private final Storage storage;
    private final Ui ui;
    private final TaskList taskList;

    public Command(Storage storage, Ui ui, TaskList taskList) {
        this.storage = storage;
        this.ui = ui;
        this.taskList = taskList;
    }

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

    // Add event task
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

    // Mark task
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

    // Un-mark task
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

    /**
     * Breaks down user's input into keyword to find.
     * Search up keyword in the original task list and return a new filtered task list.
     * Prints all tasks that matches the keyword if search is successful, otherwise,
     * prints a failed search message.
     *
     * @param input User's input from the keyboard.
     * @return All tasks that matches the keyword in String format if search is successful, otherwise,
     * prints a failed search message.
     */
    public String handleFind(String input) {
        try {
            // breaks down input to obtain keyword to search for
            String keyword = Parser.parseFind(input);

            // search up key word in the original task list, returning a new filtered TaskList object
            TaskList filteredList = taskList.searchTask(keyword);

            // Prints system message to inform of results
            return ui.printSearchTask(filteredList);
        } catch (InvalidTaskException | FailedSearchException e) {
            return Ui.printError(e);
        }
    }
}
