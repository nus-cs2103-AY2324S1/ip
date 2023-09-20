package noac.util;

import noac.command.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parser class meant to parse strings into relevant data such as the user input and date string.
 */
public class Parser {

    private static final String MARK_COMMAND = "mark";
    private static final String DEFAULT_TIME = "0000";

    /**
     * Parse the user input and return the relevant command given by the user.
     *
     * @param fullCommand The user input.
     * @return The correct command to be executed based on input.
     * @throws NoacException For any errors that needs to be displayed to user.
     */
    public static Command parse(String fullCommand) throws NoacException {
        Command returnCommand;

        String[] userInputArr = fullCommand.split(" ");
        String command = userInputArr[0];

        switch (command) {
            case "list":
                returnCommand = handleCaseList();
                break;
            case "mark":
            case "unmark":
                returnCommand = handleCaseMark(userInputArr, command);
                break;
            case "todo":
                returnCommand = handleCaseTodo(userInputArr);
                break;
            case "deadline":
                returnCommand = handleCaseDeadline(userInputArr);
                break;
            case "event":
                returnCommand = handleCaseEvent(userInputArr);
                break;
            case "delete":
                returnCommand = handleCaseDelete(userInputArr);
                break;
            case "on":
                returnCommand = handleCaseOn(userInputArr);
                break;
            case "bye":
                returnCommand = handleCaseBye();
                break;
            case "find":
                returnCommand = handleCaseFind(userInputArr);
                break;
            case "tag":
                returnCommand = handleCaseTag(userInputArr);
                break;
            default:
                throw new NoacException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return returnCommand;
    }

    /**
     * Parse date string to LoaclDateTime object.
     *
     * @param date The string date to be parsed.
     * @return The parsed LocalDateTime object.
     * @throws DateTimeParseException Exception if the parsing fails.
     */
    public static LocalDateTime parseDate(String date) throws DateTimeParseException {
        LocalDateTime localDateTime;

        if (date.length() == 10) {
            localDateTime = LocalDateTime.parse(date + " " + DEFAULT_TIME, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } else {
            localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        }

        return localDateTime;
    }


    private static Command handleCaseList() {
        return new ListCommand();
    }

    private static Command handleCaseMark(String[] userInputArr, String command) throws NoacException {
        if (userInputArr.length > 2 || userInputArr.length <= 1) {
            throw new NoacException("OOPS!!! Please enter in the format mark [TASK_NUMBER] e.g. mark 1");
        } else if (!userInputArr[1].matches("\\d+")) {
            throw new NoacException("OOPS!!! Please enter in the format mark [TASK_NUMBER] e.g. mark 1");
        }

        int taskNo = Integer.parseInt(userInputArr[1]) - 1;

        return new MarkCommand(taskNo, command.equals(MARK_COMMAND));
    }

    private static Command handleCaseTodo(String[] userInputArr) throws NoacException {
        if (userInputArr.length <= 1) {
            throw new NoacException("OOPS!!! The description of a todo cannot be empty.");
        }

        String description = "";

        for (int i = 1; i < userInputArr.length; i++) {
            description += userInputArr[i] + " ";
        }

        assert description.length() > 1 : "Description too short";

        description = description.substring(0, description.length() - 1);

        return new TodoCommand(description);
    }

    private static Command handleCaseDeadline(String[] userInputArr) throws NoacException {
        String description = "";
        String by = "";

        boolean isAfterBy = false;

        for (int i = 1; i < userInputArr.length; i++) {
            if (userInputArr[i].equals("/by")) {
                isAfterBy = true;
                continue;
            }
            if (isAfterBy) {
                by += userInputArr[i] + " ";
            } else {
                description += userInputArr[i] + " ";
            }
        }

        if (!isAfterBy) {
            throw new NoacException("OOPS!!! The input must contain the command /by");
        }
        if (by.length() == 0 || description.length() == 0) {
            throw new NoacException("OOPS!!! The description and by of a deadline cannot be empty");
        }

        assert isAfterBy : "isAfterBy must be true";

        by = by.substring(0, by.length() - 1);
        description = description.substring(0, description.length() - 1);

        return new DeadlineCommand(description, parseDate(by));
    }

    private static Command handleCaseEvent(String[] userInputArr) throws NoacException {
        String description = "";
        String from = "";
        String to = "";
        String status = "event";

        for (int i = 1; i < userInputArr.length; i++) {
            if (userInputArr[i].equals("/from")) {
                status = "from";
                continue;
            }
            if (userInputArr[i].equals("/to")) {
                status = "to";
                continue;
            }

            if (status.equals("event")) {
                description += userInputArr[i] + " ";
            } else if (status.equals("from")) {
                from += userInputArr[i] + " ";
            } else if (status.equals("to")) {
                to += userInputArr[i] + " ";
            }
        }

        if (!status.equals("to")) {
            throw new NoacException("OOPS!!! The input must contain the command /from and /to \nin this order");
        }
        if (description.length() == 0 || from.length() == 0 || to.length() == 0) {
            throw new NoacException("OOPS!!! The description, from and to of a event cannot \nbe empty!");
        }

        assert from.length() > 1 : "from too short";
        assert to.length() > 1 : "to too short";
        assert description.length() > 1 : "descript too short";

        from = from.substring(0, from.length() - 1);
        to = to.substring(0, to.length() - 1);
        description = description.substring(0, description.length() - 1);

        return new EventCommand(description, parseDate(from), parseDate(to));

    }

    private static Command handleCaseDelete(String[] userInputArr) throws NoacException {
        if (userInputArr.length > 2 || userInputArr.length <= 1) {
            throw new NoacException("OOPS!!! Please enter in the format delete [TASK_NUMBER] e.g. delete 1");

        } else if (!userInputArr[1].matches("\\d+")) {
            throw new NoacException("OOPS!!! Please enter in the format delete [TASK_NUMBER] e.g. delete 1");
        }

        int taskNo = Integer.parseInt(userInputArr[1]) - 1;

        return new DeleteCommand(taskNo);
    }

    private static Command handleCaseOn(String[] userInputArr) throws NoacException {
        LocalDate localDate;

        if (userInputArr.length != 2) {
            throw new NoacException("OOPS!!! Please input date in this format yyyy-MM-dd");
        }

        try {
            localDate = LocalDate.parse(userInputArr[1], DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        } catch (DateTimeParseException exception) {
            throw new NoacException("OOPS!!! Please input date in this format yyyy-MM-dd");
        }

        return new OnCommand(localDate);
    }

    private static Command handleCaseBye() {
        return new ExitCommand();
    }

    private static Command handleCaseFind(String[] userInputArr) {
        String searchString = "";
        for (int i = 1; i < userInputArr.length; i++) {
            searchString += userInputArr[i] + " ";
        }

        assert searchString.length() >= 1 : "searchString too short";

        return new FindCommand(searchString.trim());
    }

    private static Command handleCaseTag(String[] userInputArr) throws NoacException {
        if (userInputArr.length > 3 || userInputArr.length <= 1) {
            throw new NoacException("OOPS!!! Please enter in the format tag [TASK_NUMBER] #[TAG] e.g. tag 1 #fun");
        } else if (!userInputArr[1].matches("\\d+")) {
            throw new NoacException("OOPS!!! Please enter in the format tag [TASK_NUMBER] #[TAG] e.g. tag 1 #fun");
        } else if (userInputArr[2].charAt(0) != '#') {
            throw new NoacException("OOPS!!! Please enter in the format tag [TASK_NUMBER] #[TAG] e.g. tag 1 #fun");
        }

        int taskNo = Integer.parseInt(userInputArr[1]) - 1;

        return new TagCommand(taskNo, userInputArr[2]);
    }

}
