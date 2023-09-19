package uke;

import uke.exception.UkeException;
import uke.exception.UkeInvalidCommandException;
import uke.exception.UkeInvalidDateException;
import uke.exception.UkeInvalidDateTimeException;
import uke.exception.UkeInvalidTaskNumberException;
import uke.exception.UkeMissingArgumentException;
import uke.task.Deadline;
import uke.task.Event;
import uke.task.Task;
import uke.task.TaskList;
import uke.task.Todo;

import javafx.application.Platform;
import javafx.scene.image.Image;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Represents the Uke chatbot.
 */
public class Uke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image uke = new Image(this.getClass().getResourceAsStream("/images/Uke.png"));

    /**
     * Constructor to initialise a Uke object.
     *
     * @param path File path to the text file which stores task information.
     */
    public Uke(String path) {
        assert path != null;

        this.ui = new Ui();
        this.storage = new Storage(path);

        try {
            tasks = new TaskList(storage.loadTaskList());
        } catch (UkeException e) {
            ui.printError(e);
        }
    }

    /**
     * Generates Uke's response to the input entered by the user.
     *
     * @param input String representation of the input entered by the user.
     * @return String representation of Uke's response.
     */
    public String getResponse(String input) {
        try {
            String command = Parser.parseCommand(input);
            String info = "";

            switch (command.toUpperCase()) {
                case "BYE":
                    return handleExit();
                case "LIST":
                    return handleList();
                case "MARK":
                    info = Parser.parseInfo(input);
                    return handleMarking(info);
                case "UNMARK":
                    info = Parser.parseInfo(input);
                    return handleUnmarking(info);
                case "DELETE":
                    info = Parser.parseInfo(input);
                    return handleDelete(info);
                case "TODO":
                    info = Parser.parseInfo(input);
                    return addTodo(info);
                case "EVENT":
                    info = Parser.parseInfo(input);
                    return addEvent(info);
                case "DEADLINE":
                    info = Parser.parseInfo(input);
                    return addDeadline(info);
                case "FIND":
                    info = Parser.parseInfo(input);
                    return handleFind(info);
                case "VIEW":
                    info = Parser.parseInfo(input);
                    return handleView(info);
                default:
                    throw new UkeInvalidCommandException(command);
            }
        } catch (Exception e) {
            return ui.printError(e);
        }
    }

    /**
     * Exits the Uke chatbot.
     *
     * @return String representation of Uke's response to the exit command.
     */
    public String handleExit() throws UkeException {
        storage.update(tasks);
        new Timer().schedule(new TimerTask() {
            public void run () { Platform.exit(); }
        }, 5000);
        return ui.printExit();
    }

    /**
     * Returns Uke's response to the list command.
     *
     * @return String representation of Uke's response to the list command.
     */
    public String handleList() {
        return ui.printList(tasks, true);
    }

    /**
     * Marks task as done.
     *
     * @param info Task number of the task to be marked as done.
     * @return String representation of Uke's response to the mark command.
     * @throws UkeException If index is not an integer or if index is an integer that is not a valid task number.
     */
    public String handleMarking(String info) throws UkeException {
        int index;

        try {
            index = Integer.parseInt(info);
            Task t = tasks.getTask(index - 1);
            t.markAsDone();
            storage.update(tasks);
            return ui.printMark(t);
        } catch (NumberFormatException nfe) {
            throw new UkeInvalidTaskNumberException(info);
        } catch (IndexOutOfBoundsException e) {
            throw new UkeInvalidTaskNumberException(info);
        }
    }

    /**
     * Marks task as undone.
     *
     * @param info Task number of the task to be marked as undone.
     * @return String representation of Uke's response to the unmark command.
     * @throws UkeException If index is not an integer or if index is an integer that is not a valid task number.
     */
    public String handleUnmarking(String info) throws UkeException {
        int index;

        try {
            index = Integer.parseInt(info);
            Task t = tasks.getTask(index - 1);
            t.markAsUndone();
            storage.update(tasks);
            return ui.printUnmark(t);
        } catch (NumberFormatException nfe) {
            throw new UkeInvalidTaskNumberException(info);
        } catch (IndexOutOfBoundsException e) {
            throw new UkeInvalidTaskNumberException(info);
        }
    }

    /**
     * Deletes task.
     *
     * @param info Task number of the task to be deleted.
     * @return String representation of Uke's response to the delete command.
     * @throws UkeException If index is not an integer or if index is an integer that is not a valid task number.
     */
    public String handleDelete(String info) throws UkeException {
        int index;

        try {
            index = Integer.parseInt(info);
            Task t = tasks.getTask(index - 1);
            tasks.deleteTask(index - 1);
            storage.update(tasks);
            return ui.printDelete(t, tasks);
        } catch (NumberFormatException nfe) {
            throw new UkeInvalidTaskNumberException(info);
        } catch (IndexOutOfBoundsException e) {
            throw new UkeInvalidTaskNumberException(info);
        }
    }

    /**
     * Adds todo task to task list.
     *
     * @param info Description of todo.
     * @return String representation of Uke's response to the todo command.
     * @throws UkeException If length of info is less than 1.
     */
    public String addTodo(String info) throws UkeException {
        if (info.length() < 1) {
            throw new UkeMissingArgumentException();
        }

        Todo todo = new Todo(info);
        tasks.addTask(todo);
        storage.update(tasks);
        return ui.printAdd(todo, tasks);
    }

    /**
     * Adds deadline task to task list.
     *
     * @param info Description and due date and time of deadline task.
     * @return String representation of Uke's response to the deadline command.
     * @throws UkeException If info is missing arguments or if date and time entered is wrongly formatted.
     */
    public String addDeadline(String info) throws UkeException {
        String[] strArr = info.split(" /by ");

        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            String dlName = strArr[0];
            LocalDateTime dlTime = LocalDateTime.parse(strArr[1], dateTimeFormatter);
            Deadline dl = new Deadline(dlName, dlTime);

            tasks.addTask(dl);
            storage.update(tasks);
            return ui.printAdd(dl, tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new UkeMissingArgumentException();
        } catch (DateTimeParseException e) {
            throw new UkeInvalidDateTimeException();
        }
    }

    /**
     * Adds event to task list.
     *
     * @param info Description, start date and time and end date and time of event.
     * @return String representation of Uke's response to the event command.
     * @throws UkeException If info is missing arguments or if date and time entered is wrongly formatted.
     */
    public String addEvent(String info) throws UkeException {
        String[] strArr = info.split(" /from ");

        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            String eName = strArr[0];
            String[] dueDateArr = strArr[1].split(" /to ");
            LocalDateTime eFrom = LocalDateTime.parse(dueDateArr[0], dateTimeFormatter);
            LocalDateTime eTo = LocalDateTime.parse(dueDateArr[1], dateTimeFormatter);
            Event event = new Event(eName, eFrom, eTo);

            tasks.addTask(event);
            storage.update(tasks);
            return ui.printAdd(event, tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new UkeMissingArgumentException();
        } catch (DateTimeParseException e) {
            throw new UkeInvalidDateTimeException();
        }
    }

    /**
     * Finds and returns the list of tasks containing the given keyword.
     *
     * @param info Keyword entered by user.
     * @return String representation of Uke's response to the find command.
     * @throws UkeMissingArgumentException If keyword is empty.
     */
    public String handleFind(String info) throws UkeMissingArgumentException {
        if (info.length() < 1) {
            throw new UkeMissingArgumentException();
        }

        TaskList list = tasks.getTaskListWithKeyword(info);
        return ui.printList(list, false);
    }

    /**
     * Finds and returns the list of tasks that start on or is due by the given date.
     *
     * @param info Date in DD/MM/YYYY entered by user.
     * @return String representation of Uke's response to the view command.
     * @throws UkeInvalidDateException If date entered is invalid or in an invalid format.
     */
    public String handleView(String info) throws UkeInvalidDateException {
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate viewingDate = LocalDate.parse(info, dateTimeFormatter);

            TaskList matchingTasks = tasks.getTaskListWithDate(viewingDate);
            matchingTasks.sortByTime();

            return ui.printSchedule(viewingDate, matchingTasks);
        } catch (DateTimeParseException e) {
            throw new UkeInvalidDateException();
        }
    }

}
