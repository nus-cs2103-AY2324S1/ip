package duke.parser;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import duke.DukeException;
import duke.command.AddCommand;
import duke.command.AllCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DateCommand;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Provides static methods to parse user queries and data file.
 */
public class Parser {
    private static final String EMPTY_DEADLINE_DATE = "Please specify deadline date using /by\n"
        + "e.g. deadline report /by 2023-12-31";
    private static final String EMPTY_EVENT_DATE = "Please specify event start and end dates using /from and /to\n"
        + "e.g. event holiday /from 2023-06-01 /to 2023-06-30";
    private static final String EMPTY_DEADLINE_DESC = "The description of a deadline cannot be empty.\n"
        + "e.g. deadline report /by 2023-12-31";
    private static final String EMPTY_EVENT_DESC = "The description of a event cannot be empty.\n"
        + "e.g. event holiday /from 2023-06-01 /to 2023-06-30";
    private static final String EMPTY_KEYWORD = "What are you trying to find?\n"
        + "e.g. find holiday";
    private static final String EMPTY_DELETE_INDEX = "Please specify the index of which task you would "
        + "like to delete.\ne.g. delete 3";
    private static final String EMPTY_MARK_INDEX = "Please specify the index of which task you would like to mark.\n"
        + "e.g. mark 3";
    private static final String EMPTY_UNMARK_INDEX = "Please specify the index of which task you would "
        + "like to unmark.\ne.g. unmark 3";
    private static final String EMPTY_TODO_DESC = "The description of a todo cannot be empty.\n"
        + "e.g. todo workout";
    private static final String ERROR_DATE_PARSE_FILE = "Error parsing date in file";
    private static final String ERROR_NUMBER_FORMAT = "Seems like it's not a number.";
    private static final String ERROR_PARSING_FILE = "Error parsing file data";
    private static final String INVALID_COMMAND = "I'm sorry, but I don't know what that means :(";
    private static final String INVALID_DATE = "Please provide a valid date with the following format: YYYY-MM-DD";
    private static final String INVALID_END_DATE = "Your end date is before start date";
    private static final String INVALID_QUERY_LIST = "Invalid query list of 0 length.";
    private static final String OVERPARAMETER = "You provide too many parameters for this command.";

    /**
     * Converts a String date from YYYY-MM-DD to d MMM yyyy format.
     * @param dateStr String date of YYYY-MM-DD format
     * @return String date of d MMM yyyy format
     * @throws DukeException if invalid date
     */
    public static String convertToDmy(String dateStr) throws DukeException {
        try {
            LocalDate date = LocalDate.parse(dateStr);
            return date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        } catch (DateTimeException e) {
            throw new DukeException(INVALID_DATE);
        }
    }

    /**
     * Converts any string separated by white spaces to List of String.
     * @param s String separated by white spaces
     * @return List of String
     */
    private static List<String> convertToList(String s) {
        String[] queries = s.trim().split("\\s+");
        List<String> queryList = Arrays.asList(queries);
        return queryList;
    }

    /**
     * Parses each line of String in data file into Task.
     * @param s Line of String in data file
     * @return Task
     * @throws DukeException if String cannot be parsed due to invalid
     *     format or invalid date
     */
    public static Task parseFile(String s) throws DukeException {
        String[] q = s.trim().split(",>");
        List<String> queryList = Arrays.asList(q);
        assert queryList.size() > 0 : INVALID_QUERY_LIST;
        try {
            switch (q[0]) {
            case "deadline":
                return new Deadline(q[1], q[2].equals("X"), LocalDate.parse(q[3]));
            case "event":
                return new Event(q[1], q[2].equals("X"), LocalDate.parse(q[3]), LocalDate.parse(q[4]));
            case "todo":
                return new ToDo(q[1], q[2].equals("X"));
            default:
                throw new DukeException(ERROR_PARSING_FILE);
            }
        } catch (DateTimeException e) {
            throw new DukeException(ERROR_DATE_PARSE_FILE);
        }
    }

    /**
     * Parses user query of deadline into a Deadline instance.
     * @param queryList User query list
     * @return Deadline instance
     * @throws DukeException if no deadline description, invalid format, or invalid date
     */
    private static Deadline parseUserDeadline(List<String> queryList) throws DukeException {
        assert queryList.size() > 0 : INVALID_QUERY_LIST;
        if (queryList.size() < 2) {
            throw new DukeException(EMPTY_DEADLINE_DESC);
        }
        String name = "";
        String deadlineString = "";
        int byIndex = -1;
        for (int i = 1; i < queryList.size(); i++) {
            if (queryList.get(i).equals("/by")) {
                byIndex = i;
                continue;
            }
            if (byIndex == -1) {
                name += i > 1 ? " " : "";
                name += queryList.get(i);
            } else {
                deadlineString += i > byIndex + 1 ? " " : "";
                deadlineString += queryList.get(i);
            }
        }
        if (deadlineString.equals("")) {
            throw new DukeException(EMPTY_DEADLINE_DATE);
        }
        try {
            LocalDate deadline = LocalDate.parse(deadlineString);
            return new Deadline(name, deadline);
        } catch (DateTimeException e) {
            throw new DukeException(INVALID_DATE);
        }
    }

    /**
     * Parses user query of event into an Event instance.
     * @param queryList User query list
     * @return Event instance
     * @throws DukeException if no event description, invalid format, or invalid date
     */
    private static Event parseUserEvent(List<String> queryList) throws DukeException {
        assert queryList.size() > 0 : INVALID_QUERY_LIST;
        if (queryList.size() < 2) {
            throw new DukeException(EMPTY_EVENT_DESC);
        }
        String name = "";
        String fromStr = "";
        String toStr = "";
        int fromIndex = -1;
        int toIndex = -1;
        for (int i = 1; i < queryList.size(); i++) {
            if (queryList.get(i).equals("/from")) {
                fromIndex = i;
                toIndex = -1;
                continue;
            }
            if (queryList.get(i).equals("/to")) {
                toIndex = i;
                fromIndex = -1;
                continue;
            }
            if (fromIndex == -1 && toIndex == -1) {
                name += i > 1 ? " " : "";
                name += queryList.get(i);
            } else if (fromIndex > -1) {
                fromStr += i > fromIndex + 1 ? " " : "";
                fromStr += queryList.get(i);
            } else {
                toStr += i > toIndex + 1 ? " " : "";
                toStr += queryList.get(i);
            }
        }
        return checkEvent(name, fromStr, toStr);
    }

    /**
     * Checks event parameters to be valid.
     * @param name event name
     * @param fromStr from date
     * @param toStr to date
     * @return event
     * @throws DukeException if invalid parameters
     */
    private static Event checkEvent(String name, String fromStr, String toStr) throws DukeException {
        if (fromStr.equals("") || toStr.equals("")) {
            throw new DukeException(EMPTY_EVENT_DATE);
        }
        try {
            LocalDate from = LocalDate.parse(fromStr);
            LocalDate to = LocalDate.parse(toStr);
            if (from.compareTo(to) > 0) {
                throw new DukeException(INVALID_END_DATE);
            }
            return new Event(name, from, to);
        } catch (DateTimeException e) {
            throw new DukeException(INVALID_DATE);
        }
    }

    /**
     * Parses user query of todo into an ToDo instance.
     * @param queryList User query list
     * @return ToDo instance
     * @throws DukeException if no todo description
     */
    public static ToDo parseUserToDo(List<String> queryList) throws DukeException {
        assert queryList.size() > 0 : INVALID_QUERY_LIST;
        if (queryList.size() < 2) {
            throw new DukeException(EMPTY_TODO_DESC);
        }
        String name = "";
        for (int i = 1; i < queryList.size(); i++) {
            name += i > 1 ? " " : "";
            name += queryList.get(i);
        }
        return new ToDo(name);
    }

    /**
     * Parses user query list of Find command.
     * @param queryList User query list
     * @return Keyword of what user searches
     * @throws DukeException if no keywoard given
     */
    public static String parseUserFind(List<String> queryList) throws DukeException {
        assert queryList.size() > 0 : INVALID_QUERY_LIST;
        if (queryList.size() < 2) {
            throw new DukeException(EMPTY_KEYWORD);
        }
        String keyword = "";
        for (int i = 1; i < queryList.size(); i++) {
            keyword += i > 1 ? " " : "";
            keyword += queryList.get(i);
        }
        return keyword;
    }

    /**
     * Parses user input and returns the corresponding command.
     * @param input
     * @return Command to execute
     * @throws DukeException if invalid user input
     */
    public static Command parse(String input) throws DukeException {
        List<String> queryList = convertToList(input);
        int size = queryList.size();
        assert size > 0 : INVALID_QUERY_LIST;
        switch (queryList.get(0)) {

        case "":
            return new AllCommand();

        case "10":
        case "b":
        case "bye":
            if (size > 1) {
                throw new DukeException(OVERPARAMETER);
            }
            return new ByeCommand();

        case "8":
        case "a":
        case "date":
            if (size < 2) {
                throw new DukeException(INVALID_DATE);
            }
            return new DateCommand(queryList.get(1));

        case "2":
        case "d":
        case "deadline":
            return new AddCommand(parseUserDeadline(queryList));

        default:
            return parseHelper(queryList);
        }
    }

    private static Command parseHelper(List<String> queryList) throws DukeException {
        int size = queryList.size();
        switch (queryList.get(0)) {

        case "3":
        case "e":
        case "event":
            return new AddCommand(parseUserEvent(queryList));

        case "7":
        case "f":
        case "find":
            String keyword = Parser.parseUserFind(queryList);
            return new FindCommand(keyword);

        case "1":
        case "l":
        case "list":
            if (size > 1) {
                throw new DukeException(OVERPARAMETER);
            }
            return new ListCommand();

        case "5":
        case "m":
        case "mark":
            if (size > 2) {
                throw new DukeException(OVERPARAMETER);
            }
            try {
                int index = Integer.parseInt(queryList.get(1)) - 1;
                return new MarkCommand(index);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException(EMPTY_MARK_INDEX);
            } catch (NumberFormatException e) {
                throw new DukeException(ERROR_NUMBER_FORMAT);
            }

        default:
            return parseHelper2(queryList);
        }
    }

    private static Command parseHelper2(List<String> queryList) throws DukeException {
        int size = queryList.size();
        switch (queryList.get(0)) {

        case "9":
        case "del":
        case "delete":
            if (size > 2) {
                throw new DukeException(OVERPARAMETER);
            }
            try {
                int index = Integer.parseInt(queryList.get(1)) - 1;
                return new DeleteCommand(index);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException(EMPTY_DELETE_INDEX);
            } catch (NumberFormatException e) {
                throw new DukeException(ERROR_NUMBER_FORMAT);
            }

        case "4":
        case "t":
        case "todo":
            return new AddCommand(parseUserToDo(queryList));

        case "6":
        case "u":
        case "unmark":
            if (size > 2) {
                throw new DukeException(OVERPARAMETER);
            }
            try {
                int index = Integer.parseInt(queryList.get(1)) - 1;
                return new UnmarkCommand(index);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException(EMPTY_UNMARK_INDEX);
            } catch (NumberFormatException e) {
                throw new DukeException(ERROR_NUMBER_FORMAT);
            }

        default:
            throw new DukeException(INVALID_COMMAND);
        }
    }
}
