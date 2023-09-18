package duke;

import java.time.LocalDateTime;
import java.util.List;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskStorage;
import duke.task.Todo;
import duke.ui.Parser;
import duke.ui.Ui;

/**
 * Duke class handles main logic for the chatbot.
 */
public class Duke {
    private final TaskList taskList;
    private final Ui ui;

    /**
     * Initialise Duke chatbot instance.
     * @param ui Ui type that Duke will use.
     * @throws DukeException
     */
    public Duke(Ui ui) throws DukeException {
        this.taskList = new TaskList(new TaskStorage());
        this.ui = ui;
    }

    /**
     * Passes input to duke to handle.
     * @param input String input to handle.
     * @return isRunning boolean to decide if Duke should continue.
     * @throws DukeException If error encountered while handling input.
     */
    public boolean handleInput(String input) throws DukeException {
        Parser parser = Parser.from(input);
        assert parser != null : "The variable 'parser' is null";

        switch (parser.getCommand()) {
        case "bye":
            return false;
        case "list":
            listCommand();
            break;
        case "mark":
            markCommand(parser);
            break;
        case "unmark":
            unmarkCommand(parser);
            break;
        case "delete":
            deleteCommand(parser);
            break;
        case "todo":
            todoCommand(parser);
            break;
        case "deadline":
            deadlineCommand(parser);
            break;
        case "event":
            eventCommand(parser);
            break;
        case "find":
            findCommand(parser);
            break;
        default:
            invalidCommand(parser);
            break;
        }

        return true;
    }

    private void listCommand() {
        ui.listTasks(taskList.getTasks());
    }

    private void markCommand(Parser parser) throws DukeException {
        Task task = taskList.markTask(parser.getArgAsInt());
        ui.markTask(task);
    }

    private void unmarkCommand(Parser parser) throws DukeException {
        Task task = taskList.unmarkTask(parser.getArgAsInt());
        ui.unmarkTask(task);
    }

    private void deleteCommand(Parser parser) throws DukeException {
        Task task = taskList.deleteTask(parser.getArgAsInt());
        ui.deleteTask(task);
    }

    private void todoCommand(Parser parser) throws DukeException {
        String todoName = parser.getArg();
        if (todoName == null || todoName.equals("")) {
            throw new DukeException("duke.Todo name cannot be empty");
        }
        Task task = new Todo(todoName);
        taskList.addTask(task);
        ui.addTask(task);
    }

    private void deadlineCommand(Parser parser) throws DukeException {
        String deadlineName = parser.getArg();
        if (deadlineName == null || deadlineName.equals("")) {
            throw new DukeException("duke.Deadline name cannot be empty");
        }

        LocalDateTime deadline;
        deadline = parser.getOptArgAsDateTime("by");

        if (deadline == null) {
            throw new DukeException("Use /by to specify deadline date (yyyy/MM/dd [HHmm])");
        }

        Task task = new Deadline(deadlineName, deadline);
        taskList.addTask(task);
        ui.addTask(task);
    }

    private void eventCommand(Parser parser) throws DukeException {
        String eventName = parser.getArg();
        if (eventName == null || eventName.equals("")) {
            throw new DukeException("duke.Deadline name cannot be empty");
        }

        LocalDateTime from;
        LocalDateTime to;
        from = parser.getOptArgAsDateTime("from");
        to = parser.getOptArgAsDateTime("to");

        if (from == null || to == null) {
            throw new DukeException("Use /from and /to to specify event duration (yyyy/MM/dd [HHmm])");
        }

        Task task = new Event(eventName, from, to);
        taskList.addTask(task);
        ui.addTask(task);
    }

    private void findCommand(Parser parser) {
        String search = parser.getArg();
        List<Task> tasks = taskList.findTasks(search);
        ui.listTasks(tasks);
    }

    private void invalidCommand(Parser parser) {
        ui.invalidCommand(parser.getCommand());
    }


    /**
     * Gracefully handle exception thrown by Duke.
     * @param e Exception thrown by Duke.
     */
    public void handleException(DukeException e) {
        this.ui.printException(e);
    }
}
