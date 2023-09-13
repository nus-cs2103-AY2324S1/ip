package duke.components;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidDeadlineException;
import duke.exceptions.InvalidEventException;
import duke.exceptions.InvalidFindTaskException;
import duke.exceptions.InvalidListFlagException;
import duke.exceptions.InvalidStartEndException;
import duke.exceptions.InvalidTaskIdException;
import duke.exceptions.NoDescException;
import duke.exceptions.NoEndException;
import duke.exceptions.NoStartException;
import duke.exceptions.NoTaskIdException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;

/**
 * Interprets user commands, and controls what the user can or cannot do. An object acts as a
 * parser to parse commands and carry them out.
 */
public class Parser {
    private TaskList tasks;
    private Ui ui;

    /**
     * Class constructor for Parser.
     *
     * @param tasks tasks in the list.
     * @param ui    ui to be used.
     */
    public Parser(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Returns true if the string can be parsed as an integer.
     *
     * @param s the string to be tested.
     * @return true if s can be parsed as an integer.
     */
    //credit: https://www.freecodecamp.org/news/java-string-to-int-how-to-convert-a-string-to-an-integer/
    private static boolean isNumber(String s) {
        return s != null && s.matches("[0-9.]+");
    }

    /**
     * Converts a string of the format YYYY-MM-dd HH:mm to a LocalDateTime object.
     *
     * @param str a datetime string.
     * @return the corresponding LocalDateTime object.
     * @throws DateTimeParseException if str is not of the correct format.
     */
    public static LocalDateTime convertToDateTime(String str) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        return dateTime;
    }

    /**
     * Parses the input by user, and carries out valid commands.
     *
     * @param input user input.
     * @return a String message describing the action taken.
     * @throws DukeException          when there are invalid commands.
     * @throws DateTimeParseException when a date inputted by user is of the incorrect format.
     */
    public String parseInput(String input) throws DukeException, DateTimeParseException {
        String[] inputs = input.split(" ");
        String command = inputs[0];

        switch (command) {
        case "list":
            return parseList(input);
        case "delete":
            return parseDelete(input);
        case "mark":
            return parseMark(input);
        case "unmark":
            return parseUnMark(input);
        case "find":
            return findTask(input);
        case "todo":
            return parseToDo(input);
        case "deadline":
            return parseDeadline(input);
        case "event":
            return parseEvent(input);
        case "bye":
            return parseBye();
        default:
            throw new InvalidCommandException();
        }
    }

    /**
     * Returns the list of tasks that are within a certain period.
     * User can specify 'today', 'week' or 'fort' after the 'soon' command.
     * 'today' will list the tasks within/due today,'week' will list
     * the tasks within/due this week, while 'fort' will
     * list the tasks within/ due in 2 weeks.
     *
     * @param input user input.
     * @throws InvalidListFlagException if command is not of the correct format.
     */
    public String parseList(String input) throws InvalidListFlagException {
        String[] inputs = input.split(" ");
        if (inputs.length == 1) {
            return tasks.listTasks();
        }
        if (inputs.length != 2) {
            throw new InvalidListFlagException();
        }
        String when = inputs[1];
        String upcoming = this.tasks.getUpcoming(when);
        return upcoming;
    }

    /**
     * Handles the deleting of a specified task.
     *
     * @param input user input.
     * @throws NoTaskIdException      if no taskID is provided.
     * @throws InvalidTaskIdException if a non-numerical id is provided.
     */
    public String parseDelete(String input) throws NoTaskIdException,
            InvalidTaskIdException {
        String[] inputArr = input.split(" ");
        if (inputArr.length == 1) {
            throw new NoTaskIdException();
        }
        String strIndex = inputArr[1];
        if (!isNumber(strIndex)) {
            throw new InvalidTaskIdException();
        }

        int index = Integer.parseInt(strIndex) - 1; // index starts from 1
        return tasks.deleteTask(index);
    }

    /**
     * Handles the marking of a specified task.
     *
     * @param input user input.
     * @throws NoTaskIdException      if no taskID is provided.
     * @throws InvalidTaskIdException If a non-numerical id is provided.
     */
    public String parseMark(String input) throws NoTaskIdException,
            InvalidTaskIdException {
        String[] inputArr = input.split(" ", 2);
        if (inputArr.length == 1) {
            throw new NoTaskIdException();
        }
        String strIndex = inputArr[1];
        if (!isNumber(strIndex)) {
            throw new InvalidTaskIdException();
        }

        int index = Integer.parseInt(strIndex) - 1;
        return tasks.markTask(index);
    }

    /**
     * Handles the unmarking of a specified task.
     *
     * @param input user input.
     * @throws NoTaskIdException      if no taskID is provided.
     * @throws InvalidTaskIdException If a non-numerical id is provided.
     */
    public String parseUnMark(String input) throws NoTaskIdException,
            InvalidTaskIdException {
        String[] inputArr = input.split(" ", 2);
        if (inputArr.length == 1) {
            throw new NoTaskIdException();
        }
        String strIndex = inputArr[1];
        if (!isNumber(strIndex)) {
            throw new InvalidTaskIdException();
        }

        int index = Integer.parseInt(strIndex) - 1;
        return tasks.unMarkTask(index);
    }

    /**
     * Creates a uncompleted ToDo with the details provided.
     *
     * @param task task description.
     * @return a ToDo.
     */
    public String createToDo(String task) {
        ToDo toDo = new ToDo(Status.NOT_DONE, task);
        return tasks.addTask(toDo);
    }

    /**
     * Creates an uncompleted deadline with the details provided.
     *
     * @param task task description.
     * @param date deadline.
     * @return a Deadline.
     * @throws DateTimeParseException when the date provided is of the incorrect format.
     */
    public String createDeadline(String task, String date) throws DateTimeParseException {
        LocalDateTime dateTime = convertToDateTime(date);
        Deadline deadline = new Deadline(Status.NOT_DONE, task, dateTime);
        return tasks.addTask(deadline);
    }

    /**
     * Creates an uncompleted event with the details provided.
     *
     * @param task  task description.
     * @param start start date.
     * @param end   end date.
     * @return an Event.
     * @throws DateTimeParseException   when the dates provided are of the incorrect format.
     * @throws InvalidStartEndException when start date greater than end date.
     */
    public String createEvent(String task, String start, String end) throws DateTimeParseException,
            InvalidStartEndException {
        LocalDateTime startDateTime = convertToDateTime(start);
        LocalDateTime endDateTime = convertToDateTime(end);
        Event event = new Event(Status.NOT_DONE, task, startDateTime, endDateTime);
        return tasks.addTask(event);
    }

    /**
     * Handles the creation of a ToDo if format is correct.
     *
     * @param input user input.
     * @return a ToDo if format is correct.
     * @throws NoDescException if no description is provided.
     */
    public String parseToDo(String input) throws DukeException {
        String[] inputs = input.split(" ", 2);
        if (inputs.length == 1) {
            throw new NoDescException();
        }
        String task = inputs[1].trim();
        if (task.isBlank()) {
            throw new NoDescException();
        }
        return createToDo(task);
    }

    /**
     * Handles the creation of a Deadline if format is correct.
     *
     * @param input user input.
     * @return a Deadline if format is correct.
     * @throws NoDescException          when no description is provided.
     * @throws DateTimeParseException   when date is of the incorrect format.
     * @throws InvalidDeadlineException when deadline is of the incorrect format.
     */
    public String parseDeadline(String input) throws NoDescException,
            DateTimeParseException, InvalidDeadlineException {
        String[] inputs = input.split(" ", 2);
        if (inputs.length == 1) {
            throw new NoDescException();
        }

        String afterCommand = inputs[1];
        String[] details = afterCommand.split(" /by ", 2);
        if (details.length < 2) {
            throw new InvalidDeadlineException();
        }

        String task = details[0].trim();
        String date = details[1];
        if (task.isBlank()) {
            throw new NoDescException();
        }

        return createDeadline(task, date);
    }

    /**
     * Handles the creation of an event if format is correct.
     *
     * @param input user input.
     * @return an Event if format is correct.
     * @throws NoDescException          when no description is provided.
     * @throws InvalidEventException    when event is of the incorrect format.
     * @throws NoStartException         when no start is provided.
     * @throws NoEndException           when no end is provided.
     * @throws DateTimeParseException   when date is of the incorrect format.
     * @throws InvalidStartEndException when start greater than end date.
     */
    public String parseEvent(String input) throws NoDescException,
            InvalidEventException, NoStartException, NoEndException,
            DateTimeParseException, InvalidStartEndException {
        String[] inputs = input.split(" ", 2);
        if (inputs.length == 1) {
            throw new NoDescException();
        }
        if (inputs[1].isBlank()) {
            throw new NoDescException();
        }

        String afterCommand = inputs[1];
        String[] details = afterCommand.split("/from", 2);
        if (details[0].isBlank()) {
            throw new NoDescException();
        }
        if (details.length == 1) {
            throw new InvalidEventException(); //can either be no desc or no start
        }

        String task = details[0].trim();
        String[] dateDetails = details[1].split("/to");
        String start = dateDetails[0].trim();
        if (start.isBlank()) {
            throw new NoStartException();
        }
        if (dateDetails.length == 1) {
            throw new NoEndException();
        }
        String end = dateDetails[1].trim();
        if (end.isBlank()) {
            throw new NoEndException();
        }

        return createEvent(task, start, end);
    }

    /**
     * Handles the finding of tasks that match the specified keyword.
     *
     * @param input user input.
     * @throws InvalidFindTaskException if 0 or more than 1 keyword is specified.
     */
    public String findTask(String input) throws InvalidFindTaskException {
        String[] inputs = input.split(" ");
        if (inputs.length != 2) {
            throw new InvalidFindTaskException();
        }
        String keyword = inputs[1];
        String matches = tasks.findMatches(keyword);
        return matches;
    }

    /**
     * Returns a goodbye to the user.
     */
    public String parseBye() {
        return ui.bye();
    }
}
