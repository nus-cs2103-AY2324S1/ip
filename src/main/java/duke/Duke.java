package duke;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the Duke chatbot.
 */
public class Duke {

    private static final String FILEPATH = "./data/duke.txt";
    static boolean allowNext = true;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor to initialise a Duke object.
     *
     * @param path File path to the text file which stores task information.
     */
    public Duke(String path) {
        this.ui = new Ui();
        this.storage = new Storage(path);
        try {
            tasks = new TaskList(storage.loadTaskList());
        } catch (DukeException e) {
            ui.printError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Starts the Duke chatbot to allow for user commands.
     */
    public void run() {
        try {
            ui.printGreeting();
            storage.createDataFile();
        } catch (Exception e) {
            ui.printError(e);
        }

        while (allowNext) {
            try {
                String input = ui.getInput();
                String command = Parser.parseCommand(input);
                String info;

                switch (command.toUpperCase()) {
                case "BYE":
                    handleExit();
                    storage.update(tasks);
                    break;
                case "LIST":
                    handleList();
                    break;
                case "MARK":
                    info = Parser.parseInfo(input);
                    handleMarking(info);
                    storage.update(tasks);
                    break;
                case "UNMARK":
                    info = Parser.parseInfo(input);
                    handleUnmarking(info);
                    storage.update(tasks);
                    break;
                case "DELETE":
                    info = Parser.parseInfo(input);
                    handleDelete(info);
                    storage.update(tasks);
                    break;
                case "TODO":
                    info = Parser.parseInfo(input);
                    addTodo(info);
                    storage.update(tasks);
                    break;
                case "EVENT":
                    info = Parser.parseInfo(input);
                    addEvent(info);
                    storage.update(tasks);
                    break;
                case "DEADLINE":
                    info = Parser.parseInfo(input);
                    addDeadline(info);
                    storage.update(tasks);
                    break;
                default:
                    throw new DukeInvalidCommandException(command);
                }
            } catch (Exception e) {
                ui.printError(e);
            }
        }
    }

    /**
     * Stops the Duke chatbot.
     */
    public void handleExit() {
        ui.printExit();
        allowNext = false;
    }

    /**
     * Generates list of added tasks.
     */
    public void handleList() {
        ui.printList(tasks);
    }

    /**
     * Marks task as done.
     *
     * @param info Task number of the task to be marked as done.
     * @throws DukeException If index is not an integer or if index is an integer that is not a valid task number.
     */
    public void handleMarking(String info) throws DukeException {
        int index;

        try {
            index = Integer.parseInt(info);
            Task t = tasks.getTask(index - 1);
            t.markAsDone();
            ui.printMark(t);
        } catch (NumberFormatException nfe) {
            throw new DukeInvalidTaskNumberException(info);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidTaskNumberException(info);
        }
    }

    /**
     * Marks task as undone.
     *
     * @param info Task number of the task to be marked as undone.
     * @throws DukeException If index is not an integer or if index is an integer that is not a valid task number.
     */
    public void handleUnmarking(String info) throws DukeException {
        int index;

        try {
            index = Integer.parseInt(info);
            Task t = tasks.getTask(index - 1);
            t.markAsUndone();
            ui.printUnmark(t);
        } catch (NumberFormatException nfe) {
            throw new DukeInvalidTaskNumberException(info);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidTaskNumberException(info);
        }
    }

    /**
     * Deletes task.
     *
     * @param info Task number of the task to be deleted.
     * @throws DukeException If index is not an integer or if index is an integer that is not a valid task number.
     */
    public void handleDelete(String info) throws DukeException {
        int index;

        try {
            index = Integer.parseInt(info);
            Task t = tasks.getTask(index - 1);
            tasks.deleteTask(index - 1);
            ui.printDelete(t);
            ui.printNumberOfTasks(tasks);
        } catch (NumberFormatException nfe) {
            throw new DukeInvalidTaskNumberException(info);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidTaskNumberException(info);
        }
    }

    /**
     * Adds todo task to task list.
     *
     * @param info Description of todo.
     * @throws DukeException If length of info is less than 1.
     */
    public void addTodo(String info) throws DukeException {
        if (info.length() < 1) {
            throw new DukeMissingArgumentException();
        }

        Todo todo = new Todo(info);
        ui.printAdd(todo);
        tasks.addTask(todo);
        ui.printNumberOfTasks(tasks);
    }

    /**
     * Adds deadline task to task list.
     *
     * @param info Description and due date and time of deadline task.
     * @throws DukeException If info is missing arguments or if date and time entered is wrongly formatted.
     */
    public void addDeadline(String info) throws DukeException {
        String[] strArr = info.split(" /by ");

        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            String dlName = strArr[0];
            LocalDateTime dlTime = LocalDateTime.parse(strArr[1], dateTimeFormatter);

            Deadline dl = new Deadline(dlName, dlTime);
            ui.printAdd(dl);
            tasks.addTask(dl);
            ui.printNumberOfTasks(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateTimeException();
        }
    }

    /**
     * Adds event to task list.
     *
     * @param info Description, start date and time and end date and time of event.
     * @throws DukeException If info is missing arguments or if date and time entered is wrongly formatted.
     */
    public void addEvent(String info) throws DukeException {
        String[] strArr = info.split(" /from ");

        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            String eName = strArr[0];

            String[] dueDateArr = strArr[1].split(" /to ");
            LocalDateTime eFrom = LocalDateTime.parse(dueDateArr[0], dateTimeFormatter);
            LocalDateTime eTo = LocalDateTime.parse(dueDateArr[1], dateTimeFormatter);

            Event e = new Event(eName, eFrom, eTo);
            ui.printAdd(e);
            tasks.addTask(e);
            ui.printNumberOfTasks(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateTimeException();
        }
    }

    public static void main(String[] args) {
        new Duke(FILEPATH).run();
    }
}
