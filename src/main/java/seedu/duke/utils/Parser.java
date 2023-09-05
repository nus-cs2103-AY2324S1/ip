package seedu.duke.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.duke.exceptions.EmptyDescriptionException;
import seedu.duke.exceptions.InvalidCommandException;
import seedu.duke.exceptions.MissingKeywordException;
import seedu.duke.exceptions.NotIntegerException;
import seedu.duke.exceptions.TaskException;
import seedu.duke.tasks.Deadline;
import seedu.duke.tasks.Event;
import seedu.duke.tasks.Task;
import seedu.duke.tasks.Todo;
import seedu.duke.ui.Ui;

/**
 * Paraser class
 */
public class Parser {
    private Storage storage;
    private final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * constructor
     *
     * @param storage storage to use
     */
    public Parser(Storage storage) {
        this.storage = storage;
    }

    /**
     * ENUMS
     */
    public enum Operation {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, CHECKDATE, FIND, HELP
    }

    /**
     * Main functionality
     *
     * @param userInput user input
     * @param taskList TaskList to operate on
     * @return boolean to signal if it is the BYE opeartion or not
     */
    public String parse(String userInput, TaskList taskList) {
        Operation operation;
        try {
            try {
                operation = Operation.valueOf(userInput.toUpperCase().split(" ")[0]);
            } catch (Exception e) {
                throw new InvalidCommandException();
            }
            String toReturn;
            switch (operation) {
            case HELP:
                toReturn = Ui.help();
                break;
            case BYE:
                toReturn = taskList.exit();
                break;
            case LIST:
                toReturn = taskList.listAllTasks();
                break;
            case DELETE:
                toReturn = delete(userInput, taskList);
                break;
            case MARK:
                toReturn = mark(userInput, taskList);
                break;
            case UNMARK:
                toReturn = unmark(userInput, taskList);
                break;
            case TODO:
                toReturn = todo(userInput, taskList);
                break;
            case DEADLINE:
                toReturn = deadline(userInput, taskList);
                break;
            case EVENT:
                toReturn = event(userInput, taskList);
                break;
            case CHECKDATE:
                toReturn = checkDate(userInput, taskList);
                break;
            case FIND:
                toReturn = find(userInput, taskList);
                break;
            default:
                throw new InvalidCommandException();
            }
            storage.writeFile(taskList);
            return toReturn;
        } catch (TaskException | InvalidCommandException | EmptyDescriptionException
                 | NotIntegerException | MissingKeywordException e) {
            // System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * calls the deleteTask function in TaskList
     *
     * @param userInput user input
     * @param taskList taskList to operate on
     * @throws TaskException exception thrown
     * @throws NotIntegerException exception thrown
     */
    public String delete(String userInput, TaskList taskList) throws TaskException, NotIntegerException {
        String[] parts = userInput.split(" ", 2);
        if (!isInteger(parts[1])) {
            throw new NotIntegerException();
        }
        int taskIndex = Integer.parseInt(parts[1]);
        // error of out of bounds handled in TaskList itself
        // since the size is better/ easier to get in TaskList class
        return taskList.deleteTask(taskIndex);
    }

    /**
     * calls the mark function in taskList
     *
     * @param userInput user input
     * @param taskList tasklist
     * @throws EmptyDescriptionException exception
     * @throws NotIntegerException exception
     * @throws TaskException exception
     */
    public String mark(String userInput, TaskList taskList) throws EmptyDescriptionException,
            NotIntegerException, TaskException {
        String details = extractNoKeywordsDetails(userInput);
        if (!isInteger(details)) {
            throw new NotIntegerException();
        }
        int taskIndex = Integer.parseInt(details);
        return taskList.mark(taskIndex);
    }

    /**
     * calls the unmark function
     *
     * @param userInput user input
     * @param taskList TL
     * @throws EmptyDescriptionException exception
     * @throws TaskException exception
     * @throws NotIntegerException exception
     */
    public String unmark(String userInput, TaskList taskList) throws EmptyDescriptionException,
            TaskException, NotIntegerException {
        String details = extractNoKeywordsDetails(userInput);
        if (!isInteger(details)) {
            throw new NotIntegerException();
        }
        int taskIndex = Integer.parseInt(details);
        return taskList.unMark(taskIndex);
    }

    /**
     * adds a Todo to taskList
     *
     * @param userInput user input
     * @param taskList TaskList to operate on
     * @throws EmptyDescriptionException exception
     */
    public String todo(String userInput, TaskList taskList) throws EmptyDescriptionException {
        String details = extractNoKeywordsDetails(userInput);
        Task todoTask = new Todo(details, false);
        return taskList.addTask(todoTask);
    }

    /**
     * adds a deadline to Task List
     * @param userInput user input
     * @param taskList TaskList to operate on
     * @throws EmptyDescriptionException exception
     * @throws MissingKeywordException exception
     */
    public String deadline(String userInput, TaskList taskList) throws EmptyDescriptionException,
            MissingKeywordException {
        String details = extractTaskDetails(userInput, "deadline", "/by");
        String dateString = extractAfterKeyword(userInput, "/by");
        String toReturn = "";
        try {
            LocalDateTime date = LocalDateTime.parse(dateString.trim(), timeFormat);
            Task deadlineTask = new Deadline(details, date, false);
            toReturn = taskList.addTask(deadlineTask);
            // return toReturn;
        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date format. Please use the format yyyy-MM-dd HH:mm");
        }
        return toReturn;
    }

    /**
     * adds an Event to the TaskList
     *
     * @param userInput user input
     * @param taskList task list
     * @throws EmptyDescriptionException exception
     * @throws MissingKeywordException exception
     */
    public String event(String userInput, TaskList taskList) throws EmptyDescriptionException,
            MissingKeywordException {
        String details = extractTaskDetails(userInput, "event", "/from");
        String from = extractAfterKeyword(userInput, "/from", "/to");
        String to = extractAfterKeyword(userInput, "/to");
        String toReturn = "";
        try {
            LocalDateTime dateFrom = LocalDateTime.parse(from.trim(), timeFormat);
            LocalDateTime dateTo = LocalDateTime.parse(to.trim(), timeFormat);
            Task eventTask = new Event(details, dateFrom, dateTo, false);
            toReturn = taskList.addTask(eventTask);
        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date format. Please use the format yyyy-MM-dd HH:mm");
        }
        return toReturn;
    }

    /**
     * Returns Tasks on a specific date. calls getTasksOnDate() from TaskList
     *
     * @param userInput user input
     * @param taskList TaskList to operate on
     * @throws EmptyDescriptionException exception
     */
    public String checkDate(String userInput, TaskList taskList) throws EmptyDescriptionException {
        String details = extractNoKeywordsDetails(userInput);
        String toReturn = "";
        try {
            LocalDate detailsDate = LocalDate.parse(details.trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            toReturn = taskList.getTasksOnDate(detailsDate);
        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date format. Please use the format yyyy-MM-dd");
        }
        return toReturn;
    }

    /**
     * Returns Tasks based on a specific user input keyword
     *
     * @param userInput user input
     * @param taskList TaskList to operate on
     * @throws EmptyDescriptionException exception
     */
    public String find(String userInput, TaskList taskList) throws EmptyDescriptionException {
        String keyword = extractNoKeywordsDetails(userInput);
        return taskList.findTasks(keyword, taskList);
    }

    /**
     * Helper function to extract details for functions of:
     * mark, unmark, Todo
     *
     * @param userInput user input
     * @return details in string representation
     * @throws EmptyDescriptionException if no details
     */
    private String extractNoKeywordsDetails(String userInput) throws EmptyDescriptionException {
        String[] parts = userInput.toLowerCase().split(" ", 2);
        if (parts.length == 1 || parts[1].isBlank()) {
            throw new EmptyDescriptionException("Description cannot be empty");
        }
        return parts[1];
    }

    /**
     * Extracts the task details in between `command` and `keyword`.
     * E.g. deadline read the book /by tuesday
     *      -> extracts `read the book`
     *
     * @param userInput string input by user.
     * @param commandAndKeyword todo/deadline/event
     * @return string in between command and keyword
     */
    private String extractTaskDetails(String userInput, String... commandAndKeyword)
            throws EmptyDescriptionException, MissingKeywordException {
        String[] tokens = userInput.toLowerCase().split(commandAndKeyword[0], 2);
        if (tokens.length == 0 || tokens[1].isBlank()) {
            throw new EmptyDescriptionException("Task description cannot be empty");
        }
        String detailsWithKeyword = tokens[1];
        int endIndex = detailsWithKeyword.indexOf(commandAndKeyword[1]);
        if (endIndex == -1) {
            throw new MissingKeywordException("Missing keyword: " + commandAndKeyword[1]);
        }
        String toReturn = detailsWithKeyword.substring(0, endIndex).trim();
        if (toReturn.isEmpty()) {
            throw new EmptyDescriptionException("Task description cannot be empty");
        }
        return toReturn;
    }


    /**
     * Extracts the details after the keyword.
     * E.g. event go to school /from mon 2pm /to tues 3pm
     *      -> extracts `mon 2pm`
     *
     * @param userInput string input by user.
     * @param keywords the keywords in use
     * @return string after keyword/ between 2 keywords.
     */
    private String extractAfterKeyword(String userInput, String... keywords) throws EmptyDescriptionException {
        String[] tokens = userInput.toLowerCase().split(keywords[0]);
        if (tokens.length == 1 || tokens[1].isBlank()) {
            throw new EmptyDescriptionException("Details after " + keywords[0] + " cannot be empty");
        }
        if (keywords.length == 1) {
            return tokens[1];
        }
        // last case if there are 2 keywords, such as /from and /to
        // -> return the in between of these 2 keywords
        String[] tokensAfterSecondKeyword = tokens[1].split(keywords[1]);
        if (tokensAfterSecondKeyword[0].isBlank()) {
            throw new EmptyDescriptionException("Details after " + keywords[0] + " cannot be empty");
        }
        return tokensAfterSecondKeyword[0];
    }

    /**
     * the method isInteger to judge whether input is integer
     * @param input
     * @return boolean
     */
    private boolean isInteger(String input) {
        if (input == null) {
            return false;
        } else {
            try {
                Integer.parseInt(input);
                return true;
            } catch (NumberFormatException nfe) {
                return false;
            }
        }
    }
}
