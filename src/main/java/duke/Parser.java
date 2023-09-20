package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashMap;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.ReminderCommand;
import duke.command.TodoCommand;
import duke.command.UnrecognisedCommand;
import duke.exception.DukeBadInputException;
import duke.exception.DukeLoadingException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TodoTask;

/**
 * Handles making sense of the user command and output it in a programme
 * readable way
 */
public class Parser {

    /**
     * Formatter to parse date time
     */
    private static final DateTimeFormatter PARSE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Returns a command object
     * Factory method to parse the input and output the respective command instance
     *
     * @param input - the input string that needs to be parsed
     * @return a respective subclass of the command that can be executed
     * @throws DukeBadInputException - if the input cannot be parsed properly
     * @throws NumberFormatException - if the input cannot be converted to an int
     */
    public static Command parse(String input) throws DukeBadInputException, NumberFormatException {
        String[] splitInput = input.split(" ");
        HashMap<String, LocalDateTime> flagMap = new HashMap<>();
        String desc;

        switch (splitInput[0].toUpperCase()) {
        case "BYE":
            return new ExitCommand();
        case "HELP":
            return new HelpCommand();
        case "LIST":
            return new ListCommand();
        case "MARK":
            return new MarkCommand(true, validateIndex(splitInput));
        case "UNMARK":
            return new MarkCommand(false, validateIndex(splitInput));
        case "DELETE":
            return new DeleteCommand(validateIndex(splitInput));
        case "REMINDER":
            return new ReminderCommand(validateIndex(splitInput));
        case "FIND":
            if (splitInput.length < 2) {
                throw new DukeBadInputException(
                        "Quack doesn't understand an empty find query, please provide one!!");
            }
            // join the string to find the query
            return new FindCommand(String.join(" ", Arrays.copyOfRange(splitInput, 1, splitInput.length)));
        case "TODO":
            if (splitInput.length < 2) {
                throw new DukeBadInputException(
                        "Quack doesn't understand an empty todo description, please provide one!!");
            }
            // join the string to find the description
            return new TodoCommand(String.join(" ", Arrays.copyOfRange(splitInput, 1, splitInput.length)));
        case "DEADLINE":
            desc = Parser.findFlags(flagMap, splitInput, "/by");
            return new DeadlineCommand(flagMap.get("/by"), desc);
        case "EVENT":
            desc = Parser.findFlags(flagMap, splitInput, "/from", "/to");
            if (flagMap.get("/from").isAfter(flagMap.get("/to"))) {
                throw new DukeBadInputException("Quack does not understand a event where /to is before /from");
            }
            return new EventCommand(flagMap.get("/from"), flagMap.get("/to"), desc);
        default:
            return new UnrecognisedCommand();
        }
    }

    /**
     * Loads task from storage
     *
     * @param storedStr - the string representing the task from storage
     * @return the instance of the class
     * @throws DukeLoadingException   if there is an issue loading the task
     * @throws DateTimeParseException if there is an issue parsing the date
     */

    public static Task fromStorage(String storedStr) throws DukeLoadingException, DateTimeParseException {
        String[] content = storedStr.split(Task.SEP);

        if (content.length < 3 || content.length > 6) {
            throw new DukeLoadingException(storedStr + ", this command cannot be read");
        }

        boolean isCompleted = content[2].equals("1");
        switch (content[0]) {
        case "TODO":
            return new TodoTask(content[1], isCompleted);
        case "DEADLINE":
            if (content.length != 4) {
                throw new DukeLoadingException(storedStr + ", this command cannot be read");
            }
            return new DeadlineTask(LocalDateTime.parse(content[3]), content[1], isCompleted);
        case "EVENT":
            if (content.length != 5) {
                throw new DukeLoadingException(storedStr + ", this command cannot be read");
            }
            return new EventTask(LocalDateTime.parse(content[3]),
                    LocalDateTime.parse(content[4]), content[1], isCompleted);
        default:
            throw new DukeLoadingException(storedStr + ", this command cannot be read");
        }
    }

    /**
     * Finds the index from the split input string
     *
     * @param splitInput - the split input string
     * @return the index
     * @throws DukeBadInputException throws an error if it is a negative number
     * @throws NumberFormatException throws an error if it is not a number
     */
    private static int validateIndex(String[] splitInput) throws DukeBadInputException, NumberFormatException {
        if (splitInput.length != 2) {
            throw new DukeBadInputException(
                    String.format("Quack requires exactly one number after the %s command", splitInput[0]));
        }
        int ret = Integer.parseInt(splitInput[1]);
        if (ret <= 0) {
            throw new DukeBadInputException(
                    "Quack requires a number more than zero to carry out the command!");
        }
        return ret;
    }

    /**
     * Finds the flags and update both the flags and param field
     *
     * @param flagMap     - the place to store the flag
     * @param splitInputs - input string that has been split into words
     * @param flags       - the flags that needs to be found
     * @return the desc of the command
     * @throws DukeBadInputException  - if the flags cannot be found or without a
     *                                description
     * @throws DateTimeParseException - if the value cannot be parsed
     */
    private static String findFlags(HashMap<String, LocalDateTime> flagMap,
            String[] splitInputs, String... flags)
            throws DukeBadInputException, DateTimeParseException {
        // Check for the description of flag
        HashMap<String, String> flagValues = Parser.find(splitInputs);

        if (flagValues.get("/desc").isBlank()) {
            throw new DukeBadInputException(
                    "Quack doesn't understand an empty description, please provide one!!");
        }

        // Check for the presence of the flag
        StringBuilder missingFlag = new StringBuilder();
        boolean isMissing = false;
        for (String flag : flags) {
            String flagValue = flagValues.get(flag);
            // handles missing flag
            if (flagValue == null) {
                missingFlag.append(flag).append(", ");
                isMissing = true;
                continue;
            }
            // handles missing description
            if (flagValue.isBlank()) {
                throw new DukeBadInputException(
                        "Quack doesn't understand an empty date for " + flag + ", please provide one!!");
            }
            LocalDateTime val = LocalDateTime.parse(flagValue, Parser.PARSE_FORMAT);
            flagMap.put(flag, val);
        }
        if (isMissing) {
            throw new DukeBadInputException(
                    "Quack cant find the following required flags: "
                            + missingFlag.toString() + "please provide quack with them please");
        }

        if (flagValues.keySet().size() != flags.length + 1) {
            throw new DukeBadInputException(
                    "Too many flags! Please only include the required flags");
        }
        return flagValues.get("/desc");
    }

    /**
     * Finds the required flags in the array of strings
     *
     * @param arr   - the array of strings that you want to find the flags from
     * @return A hashmap mapping the flags to its value
     * @throws DukeBadInputException - if the flags cannot be found or without a
     *                               description
     */
    private static HashMap<String, String> find(String[] arr) throws DukeBadInputException {
        StringBuilder current = new StringBuilder();
        String flag = "/desc";
        HashMap<String, String> ret = new HashMap<>();
        for (int i = 1; i < arr.length; i++) {
            if (!arr[i].startsWith("/")) {
                current.append(" " + arr[i]);
                continue;
            }

            // Means arr[i] is a flag
            // store previous value
            ret.put(flag, current.toString().strip());

            // check if flag is already present
            if (ret.get(arr[i]) != null) {
                throw new DukeBadInputException(
                        "There are too many of the " + flag + " flag, please just provide one to quack");
            }
            flag = arr[i];
            current = new StringBuilder();
        }
        ret.put(flag, current.toString().strip());
        return ret;
    }
}
