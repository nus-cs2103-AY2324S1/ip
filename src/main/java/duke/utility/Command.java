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
}
