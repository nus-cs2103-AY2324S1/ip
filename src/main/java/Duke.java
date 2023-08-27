import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Duke {

    private static final String BOT_NAME = "SoCrates";

    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private boolean isRunning = true;

    public static void main(String[] args) {
        Duke duke = new Duke("data/tasks.txt");
        duke.run();
    }

    public Duke(String filePath) {
        ui = new Ui(BOT_NAME);
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    private void run() {
        ui.showWelcomeMessage();

        while (isRunning) {
            try {
                runCommand();
            } catch (DukeException exception) {
                ui.showErrorMessage(exception.getMessage());
            }
        }

        ui.showExitMessage();
    }

    private void runCommand() throws DukeException {
        String input = ui.getUserCommand();
        String args = input.contains(" ")
                ? input.split(" ", 2)[1]
                : "";

        if (isCommand(input, "bye")) {
            isRunning = false;
        } else if (isCommand(input, "list")) {
            performListCommand();
        } else if (isCommand(input, "clear")) {
            performClearCommand();
        } else if (isCommand(input, "todo")) {
            performTodoCommand(args);
        } else if (isCommand(input, "deadline")) {
            performDeadlineCommand(args);
        } else if (isCommand(input, "event")) {
            performEventCommand(args);
        } else if (isCommand(input, "mark")) {
            performMarkCommand(args);
        } else if (isCommand(input, "unmark")) {
            performUnmarkCommand(args);
        } else if (isCommand(input, "delete")) {
            performDeleteCommand(args);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private void printTaskAdded(Task task) {
        ui.showMessage(
                "Got it. I've added this task:",
                "\t" + task,
                "Now you have " + tasks.size() + " tasks in the list."
        );
    }

    private void addToTasks(Task task) {
        tasks.add(task);
    }

    private void saveTasks() throws DukeException {
        storage.save(tasks);
    }

    private void performListCommand() {
        ui.showTaskList(tasks.getAllTasks());
    }

    private void performClearCommand() throws DukeException {
        tasks = new TaskList();
        saveTasks();
        ui.showMessage("Got it. I've cleared all tasks.");
    }

    private void performMarkCommand(String args) throws DukeException {
        int taskNumber;

        try {
            taskNumber = Integer.parseInt(args);
        } catch (NumberFormatException exception) {
            throw new DukeException("The task number must be specified.");
        }

        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new DukeException("No such task exists.");
        }
        Task task = tasks.mark(taskNumber);

        saveTasks();
        ui.showMessage(
                "Nice! I've marked this task as done:",
                "\t " + task
        );
    }

    private void performUnmarkCommand(String args) throws DukeException {
        int taskNumber;

        try {
            taskNumber = Integer.parseInt(args);
        } catch (NumberFormatException exception) {
            throw new DukeException("The task number must be specified.");
        }

        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new DukeException("No such task exists.");
        }

        Task task = tasks.unmark(taskNumber);

        saveTasks();
        ui.showMessage(
                "Ok, I've marked this task as not done yet:",
                "\t " + task
        );
    }

    private void performDeleteCommand(String args) throws DukeException {
        int taskNumber;

        try {
            taskNumber = Integer.parseInt(args);
        } catch (NumberFormatException exception) {
            throw new DukeException("The task number must be specified.");
        }

        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new DukeException("No such task exists.");
        }

        Task task = tasks.remove(taskNumber);

        saveTasks();
        ui.showMessage(
                "Noted. I've removed this task:",
                "\t " + task
        );
    }

    private void performTodoCommand(String args) throws DukeException {
        if (args.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        Todo todo = new Todo(args);
        addToTasks(todo);
        saveTasks();
        printTaskAdded(todo);
    }

    private void performDeadlineCommand(String args) throws DukeException {
        if (args.startsWith("/by")) {
            throw new DukeException("The description of a deadline cannot be empty.");
        } else if (!args.contains(" /by ")) {
            throw new DukeException("The deadline must be specified.");
        }

        String by = args.split(" /by ", 2)[1];
        if (by.isEmpty()) {
            throw new DukeException("The deadline cannot be empty.");
        }

        String description = args.replaceFirst(" /by " + by, "");

        LocalDate localBy;

        try {
            localBy = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("The dates must be filled in \"yyyy-mm-dd\" format.");
        }

        Deadline deadline = new Deadline(description, localBy);
        addToTasks(deadline);
        saveTasks();
        printTaskAdded(deadline);

    }

    private void performEventCommand(String args) throws DukeException {
        if (args.startsWith("/from")) {
            throw new DukeException("The description of a deadline cannot be empty.");
        } else if (!args.contains(" /from ") || !args.contains(" /to ")) {
            throw new DukeException("The from and to must be specified.");
        }

        String to = args.split(" /to ", 2)[1];
        if (to.isEmpty()) {
            throw new DukeException("The to cannot be empty.");
        }
        args = args.replaceFirst(" /to " + to, "");

        String from = args.split(" /from ", 2)[1];
        if (from.isEmpty()) {
            throw new DukeException("The from cannot be empty.");
        }

        String description = args.replaceFirst(" /from " + from, "");

        if (description.isEmpty()) {
            throw new DukeException("The description of an event cannot be empty.");
        }

        LocalDate localFrom, localTo;

        try {
            localFrom = LocalDate.parse(from);
            localTo = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw new DukeException("The dates must be filled in \"yyyy-mm-dd\" format.");
        }

        Event event = new Event(description, localFrom, localTo);
        addToTasks(event);
        saveTasks();
        printTaskAdded(event);
    }

    private static boolean isCommand(String text, String command) {
        return text.equals(command) || text.startsWith(command + " ");
    }

}
