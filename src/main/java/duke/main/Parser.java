package duke.main;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteTaskCommand;
import duke.command.FindTaskCommand;
import duke.command.ListCommand;
import duke.command.MarkDoneCommand;
import duke.command.MarkNotDoneCommand;

/**
 * The Parser class.
 */
public class Parser {
    /**
     * Checks if a string is numeric.
     *
     * @param str The string to be checked.
     * @return True if the string is numeric and false otherwise.
     */
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NullPointerException | NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks for a valid "bye" command.
     *
     * @param input the string input by the user.
     * @return A duke.command.ByeCommand if the command is valid and null otherwise.
     */
    public static ByeCommand byeCommandChecker(String input) {
        if (input.trim().equals("bye")) {
            return new ByeCommand();
        } else {
            return null;
        }
    }

    /**
     * Checks for a valid "list" command.
     *
     * @param input the string input by the user.
     * @return A duke.command.ListCommand containing the keywords if the command is valid and null otherwise.
     */
    public static ListCommand listCommandChecker(String input) {
        if (input.trim().equals("list")) {
            return new ListCommand();
        } else {
            return null;
        }
    }

    /**
     * Checks for a valid "todo" command.
     *
     * @param input the string input by the user.
     * @return A duke.command.AddTodoCommand if the command is valid and null if the command is not a "todo".
     * @throws DukeException if the command is "todo" but the keywords are not valid.
     */
    public static AddTodoCommand todoCommandChecker(String input) throws DukeException {
        String[] strSegments = input.trim().split(" ");
        String command = strSegments[0];

        if (command.equals("todo")) {
            String taskName = input.trim().substring(4).trim();
            if (taskName.equals("")) {
                throw new DukeException("☹ OOPS!!! Incorrect description of a todo.");
            }
            return new AddTodoCommand(taskName);
        } else {
            return null;
        }
    }

    /**
     * Checks for a valid "deadline" command.
     *
     * @param input the string input by the user.
     * @return A duke.command.AddDeadlineCommand if the command is valid
     *         and null if the command is not a "deadline".
     * @throws DukeException if the command is "deadline" but the keywords are not valid.
     */
    public static AddDeadlineCommand deadlineCommandChecker(String input) throws DukeException {
        String[] strSegments = input.trim().split(" ");
        String command = strSegments[0];

        if (command.equals("deadline")) {
            try {
                String string = input.trim().substring(8).trim();
                String[] segments = string.split("/by");
                String eventName = segments[0].trim();
                String dateString = segments[1].trim();
                LocalDate.parse(dateString);
                return new AddDeadlineCommand(eventName, dateString);
            } catch (Exception e) {
                throw new DukeException("☹ OOPS!!! Incorrect description of a deadline.");
            }
        } else {
            return null;
        }
    }

    /**
     * Checks for a valid "event" command.
     *
     * @param input the string input by the user.
     * @return An duke.command.AddEventCommand if the command is valid and null if the command is not an "event".
     * @throws DukeException if the command is "event" but the keywords are not valid.
     */
    public static AddEventCommand eventCommandChecker(String input) throws DukeException {
        String[] strSegments = input.trim().split(" ");
        String command = strSegments[0];

        if (command.equals("event")) {
            try {
                String str = input.substring(5).trim();
                String[] segments = str.split(" /from ");
                String eventName = segments[0].trim();
                String[] segments2 = segments[1].split(" /to ");
                String startDate = segments2[0].trim();
                String endDate = segments2[1].trim();
                LocalDate.parse(startDate);
                LocalDate.parse(endDate);
                return new AddEventCommand(eventName, startDate, endDate);
            } catch (Exception e) {
                throw new DukeException("☹ OOPS!!! Incorrect description of an event.");
            }
        } else {
            return null;
        }
    }

    /**
     * Checks for a valid "mark" command.
     *
     * @param input the string input by the user.
     * @return A duke.command.MarkDoneCommand if the command is valid and null if the command is not a "mark".
     * @throws DukeException if the command is "mark" but the keywords are not valid.
     */
    public static MarkDoneCommand markCommandChecker(String input) throws DukeException {
        String[] strSegments = input.trim().split(" ");
        String command = strSegments[0];

        if (command.equals("mark")) {
            try {
                if (!isNumeric(strSegments[1])) {
                    throw new DukeException("Invalid input for index");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Invalid input for index");
            }
            int index = (int) Double.parseDouble(input.substring(4).trim());
            return new MarkDoneCommand(index);
        } else {
            return null;
        }
    }

    /**
     * Checks for a valid "unmark" command.
     *
     * @param input the string input by the user.
     * @return A duke.command.MarkNotDoneCommand if the command is valid and null if the command is not a "unmark".
     * @throws DukeException if the command is "unmark" but the keywords are not valid.
     */
    public static MarkNotDoneCommand unmarkCommandChecker(String input) throws DukeException {
        String[] strSegments = input.trim().split(" ");
        String command = strSegments[0];

        if (command.equals("unmark")) {
            try {
                if (!isNumeric(strSegments[1])) {
                    throw new DukeException("Invalid input for index");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Invalid input for index");
            }
            int index = (int) Double.parseDouble(input.substring(6).trim());
            return new MarkNotDoneCommand(index);
        } else {
            return null;
        }
    }

    /**
     * Checks for a valid "delete" command.
     *
     * @param input the string input by the user.
     * @return A duke.command.DeleteTaskCommand if the command is valid and null if the command is not a delete command.
     * @throws DukeException if the command is "delete" but the keywords are not valid.
     */
    public static DeleteTaskCommand deleteCommandChecker(String input) throws DukeException {
        String[] strSegments = input.trim().split(" ");
        String command = strSegments[0];

        if (command.equals("delete")) {
            try {
                if (!isNumeric(strSegments[1])) {
                    throw new DukeException("Invalid input for index");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Invalid input for index");
            }
            int index = (int) Double.parseDouble(input.substring(6).trim());
            return new DeleteTaskCommand(index);
        } else {
            return null;
        }
    }

    /**
     * Checks for a valid "find" command.
     *
     * @param input The string input by the user.
     * @return A duke.command.FindTaskCommand if the command is valid and null if the command is not a find command.
     * @throws DukeException if the command is "find" but the keywords are not valid
     */
    public static FindTaskCommand findTaskCommandChecker(String input) throws DukeException {
        String[] strSegments = input.trim().split(" ");
        String command = strSegments[0];

        if (command.equals("find")) {
            String keyword = input.trim().substring(4).trim();
            if (keyword.equals("")) {
                throw new DukeException("☹ OOPS!!! Incorrect description of a find command.");
            }
            return new FindTaskCommand(keyword);
        } else {
            return null;
        }
    }

    /**
     * Checks for a valid command.
     *
     * @param input the string input by the user.
     * @return a duke.command.Command corresponding to the correct command.
     * @throws DukeException if the command is invalid.
     */
    public static Command parse(String input) throws DukeException {
        if (byeCommandChecker(input) != null) {
            return byeCommandChecker(input);
        } else if (listCommandChecker(input) != null) {
            return listCommandChecker(input);
        } else if (todoCommandChecker(input) != null) {
            return todoCommandChecker(input);
        } else if (deadlineCommandChecker(input) != null) {
            return deadlineCommandChecker(input);
        } else if (eventCommandChecker(input) != null) {
            return eventCommandChecker(input);
        } else if (markCommandChecker(input) != null) {
            return markCommandChecker(input);
        } else if (unmarkCommandChecker(input) != null) {
            return unmarkCommandChecker(input);
        } else if (deleteCommandChecker(input) != null) {
            return deleteCommandChecker(input);
        } else if (findTaskCommandChecker(input) != null) {
            return findTaskCommandChecker(input);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
