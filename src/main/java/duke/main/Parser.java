package duke.main;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.command.*;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidDateFormatException;
import duke.exception.InvalidParametersException;
import duke.exception.MissingCommandException;
import duke.exception.MissingParametersException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

/** Deals with making sense of user command, manages error handling based on user input */
public class Parser {
    private static String[] splitText;

    /**
     * Interprets user input, returns Command class if found.
     * The format for this parser is "[main command] [task] /[sub command] [parameters for sub command]".
     * Order of multiple sub commands are ignored, and not case-sensitive.
     *
     * @param inputString Input String to be interpreted.
     * @return Command class to run.
     * @throws InvalidCommandException When command is invalid.
     * @throws MissingParametersException When there are missing parameters for either main or sub commands.
     * @throws InvalidParametersException When there are invalid parameters for sub commands.
     * @throws InvalidDateFormatException When there are invalid date formats for sub commands.
     * @throws MissingCommandException When sub command for main command is missing.
     */
    public static Command parse(String inputString) throws InvalidCommandException, MissingParametersException,
            InvalidParametersException, InvalidDateFormatException, MissingCommandException {
        // Split text into two.
        // With index 0 be the first word of user input, and index 1 containing the rest of the string.
        inputString = inputString.toLowerCase();
        splitText = inputString.split(" ", 2);

        String command = splitText[0];
        String task;

        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "play":
            return new PlayCommand();
        case "stop":
            return new StopCommand();
        case "pause":
            return new PauseCommand();
        case "delete":
            checkAtLeast2();
            return new DeleteCommand(splitText[1]);
        case "todo":
            checkAtLeast2();
            return new AddCommand(new ToDo(splitText[1]));
        case "event":
            checkAtLeast2();
            LocalDate from = getDateWithCommand(splitText[1], "from");
            LocalDate to = getDateWithCommand(splitText[1], "to");
            task = getTask(splitText[1]);
            return new AddCommand(new Event(task, from, to));
        case "deadline":
            checkAtLeast2();
            LocalDate by = getDateWithCommand(splitText[1], "by");
            task = getTask(splitText[1]);
            return new AddCommand(new Deadline(task, by));
        case "mark":
            checkAtLeast2();
            return new ChangeMarkCommand(splitText[1], true);
        case "unmark":
            checkAtLeast2();
            return new ChangeMarkCommand(splitText[1], false);
        case "find":
            checkAtLeast2();
            return new FindCommand(splitText[1]);
        case "tags":
            checkAtLeast2();
            return new ViewTagsCommand(splitText[1]);
        case "tag":
            checkAtLeast2();
            String[] params = getParams(splitText[1], 2);
            return new TagCommand(params[0], params[1]);
        default:
            throw new InvalidCommandException("I don't understand.");
        }
    }

    /**
     * Gets LocalDate parameter based on String sub commands.
     *
     * @param str String to search in.
     * @param command  Sub Command to search for.
     * @return LocalDate parameter based on Sub Command.
     * @throws MissingCommandException When Sub Command cannot be found.
     * @throws MissingParametersException When there are no parameters for Sub Command.
     * @throws InvalidDateFormatException When the parameter does not have a proper LocalDate format.
     */
    private static LocalDate getDateWithCommand(String str, String command) throws MissingCommandException,
            MissingParametersException, InvalidDateFormatException {
        boolean isFound = false;
        LocalDate date = null;

        String[] splitStr = str.split(" ");
        for (String word : splitStr) {
            if (!isFound) {
                if (word.equals("/" + command)) {
                    isFound = true;
                }
                continue;
            }
            try {
                date = LocalDate.parse(word);
                break;
            } catch (DateTimeParseException e) {
                throw new InvalidDateFormatException("Add a date in the foll0wing format: yyyyy-mm-dd");
            }
        }

        if (!isFound) {
            throw new MissingCommandException("Command " + command + "could not be isFound");
        } else if (date == null) {
            throw new MissingParametersException("Command " + command + " does not contain any parameters");
        }

        return date;
    }

    /**
     * Gets task based on string, under the assumption that the task will be after the main command,
     * and before the first slash command.
     *
     * @param str String to interpret.
     * @return Task name.
     * @throws MissingParametersException In the event where there are no task found.
     */
    private static String getTask(String str) throws MissingParametersException {
        StringBuilder task = new StringBuilder();

        String[] splitStr = str.split(" ");
        for (String word : splitStr) {
            if (word.charAt(0) == '/') {
                break;
            }
            System.out.println(word);
            task.append(" ").append(word);
        }

        if (task.length() < 1) {
            throw new MissingParametersException("Task not found, please type a task >:(");
        }
        return task.substring(1);
    }

    /**
     * Attempts to split string into given number of parameters.
     *
     * @param str String to split.
     * @param numOfParams Max number of words to be split.
     * @return Array of parameters.
     */
    private static String[] getParams(String str, int numOfParams) throws MissingParametersException {
        String[] splitStr = str.split(" ", numOfParams);

        if (splitStr.length < numOfParams || splitStr.length > numOfParams) {
            throw new MissingParametersException("Invalid Number of parameters, ideally " + numOfParams);
        }
        return splitStr;
    }

    /**
     * Checks length of user input. User input with length less than 2 implies
     * there are no parameters.
     *
     * @throws MissingParametersException Throws if there are no parameters found after the command.
     */
    private static void checkAtLeast2() throws MissingParametersException {
        if (splitText.length < 2) {
            throw new MissingParametersException("You need to add something after the command LOL");
        }
    }
}
