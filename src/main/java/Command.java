import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
            return ui.printError(e);
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
            return ui.printError(e);
        } catch (NumberFormatException | DateTimeException e) {
            // when time input is incorrect
            try {
                throw new InvalidDeadlineException();
            } catch (InvalidDeadlineException i) {
                return ui.printError(i);
            }
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
        } catch (MissingTimeException | EmptyTaskException e) {
            return ui.printError(e);
        } catch (NumberFormatException | DateTimeException | InvalidEventException e) {
            // when time input is incorrect
            try {
                throw new InvalidEventException();
            } catch (InvalidEventException i) {
                return ui.printError(i);
            }
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
            return this.ui.printUpdateTask(task);
        } catch (EmptyTaskException | InvalidTaskException e) {
            return ui.printError(e);
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
            return this.ui.printUpdateTask(task);
        } catch (EmptyTaskException | InvalidTaskException e) {
            return ui.printError(e);
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
            return ui.printError(e);
        }
    }
}
