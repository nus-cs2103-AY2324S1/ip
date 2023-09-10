package duke;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Main driver class for Duke project.
 * Chatbot that manages a task list.
 */
public class Duke {
    private final TaskList taskList;
    private final Ui ui;

    public Duke(Ui ui) throws DukeException {
        this.taskList = new TaskList(new TaskStorage());
        this.ui = ui;
    }

    /**
     * CLI Duke.
     * @param args Arguments from CLI (ignored).
     */
    public static void main(String[] args) {
        Duke duke;
        Ui ui = new Ui("Pong");

        try {
            duke = new Duke(ui);
        } catch (DukeException e) {
            ui.printException(e);
            return;
        }

        ui.init();

        boolean isRunning = true;
        while (isRunning) {
            try {
                String input = ui.getInput();
                isRunning = duke.handleInput(input);
            } catch (DukeException e) {
                duke.handleException(e);
            }
        }

        ui.exit();
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

        Task task;
        switch (parser.getCommand()) {
        case "bye":
            return false;
        case "list":
            ui.listTasks(taskList.getTasks());
            break;
        case "mark":
            task = taskList.markTask(parser.getArgAsInt());
            ui.markTask(task);
            break;
        case "unmark":
            task = taskList.unmarkTask(parser.getArgAsInt());
            ui.unmarkTask(task);
            break;
        case "delete":
            task = taskList.deleteTask(parser.getArgAsInt());
            ui.deleteTask(task);
            break;
        case "todo":
            String todoName = parser.getArg();
            if (todoName == null || todoName.equals("")) {
                throw new DukeException("duke.Todo name cannot be empty");
            }
            task = new Todo(todoName);
            taskList.addTask(task);
            ui.addTask(task);
            break;
        case "deadline":
            String deadlineName = parser.getArg();
            if (deadlineName == null || deadlineName.equals("")) {
                throw new DukeException("duke.Deadline name cannot be empty");
            }

            LocalDateTime deadline;
            deadline = parser.getOptArgAsDateTime("by");

            if (deadline == null) {
                throw new DukeException("Use /by to specify deadline date");
            }

            task = new Deadline(deadlineName, deadline);
            taskList.addTask(task);
            ui.addTask(task);
            break;
        case "event":
            String eventName = parser.getArg();
            if (eventName == null || eventName.equals("")) {
                throw new DukeException("duke.Deadline name cannot be empty");
            }

            LocalDateTime from;
            LocalDateTime to;
            from = parser.getOptArgAsDateTime("from");
            to = parser.getOptArgAsDateTime("to");

            if (from == null || to == null) {
                throw new DukeException("Use /from and /to to specify event duration");
            }

            task = new Event(eventName, from, to);
            taskList.addTask(task);
            ui.addTask(task);
            break;
        case "find":
            String search = parser.getArg();
            List<Task> tasks = taskList.findTasks(search);
            ui.listTasks(tasks);
            break;
        default:
            ui.invalidCommand(parser.getCommand());
            break;
        }

        return true;
    }

    /**
     * Gracefully handle exception thrown by Duke.
     * @param e Exception thrown by Duke.
     */
    public void handleException(DukeException e) {
        this.ui.printException(e);
    }
}
