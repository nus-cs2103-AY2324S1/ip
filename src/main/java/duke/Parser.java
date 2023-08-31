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
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
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
     * Formatter to output date time
     */
    public static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("EEE hh:mma, MMM yyyy");
    /**
     * Formatter to parse date time
     */
    private static final DateTimeFormatter PARSE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Factory method to parse the input and output the respective command
     *
     * @param input - the input string that needs to be parsed
     * @return a respective subclass of the command that can be executed
     * @throws DukeBadInputException - if the input cannot be parsed properly
     * @throws NumberFormatException - if the input cannot be converted to an int
     */
    public static Command parse(String input) throws DukeBadInputException, NumberFormatException {
        String[] splitInput = input.split(" ");
        HashMap<String, LocalDateTime> flagMap = new HashMap<>();

        switch (splitInput[0].toUpperCase()) {
        case "BYE":
            return new ExitCommand();
        case "HELP":
            return new HelpCommand();
        case "LIST":
            return new ListCommand();
        case "MARK":
            return new MarkCommand(true, findIndex(splitInput));
        case "UNMARK":
            return new MarkCommand(false, findIndex(splitInput));
        case "DELETE":
            return new DeleteCommand(findIndex(splitInput));
        case "TODO":
            String desc = input.replace("todo ", "");
            if (desc.equals("todo")) {
                throw new DukeBadInputException(
                        "Quack doesn't understand an empty todo description, please provide one!!");
            }
            return new TodoCommand(desc);
        case "DEADLINE":
            desc = Parser.findFlags(flagMap, splitInput, "/by");
            return new DeadlineCommand(flagMap.get("/by"), desc);
        case "EVENT":
            desc = Parser.findFlags(flagMap, splitInput, "/from", "/to");
            return new EventCommand(flagMap.get("/from"), flagMap.get("/to"), desc);
        default:
            return new UnrecognisedCommand();
        }
    }

    /**
     * Load task from storage
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
     * Find the index from the split input string
     *
     * @param splitInput - the split input string
     * @return the index
     * @throws DukeBadInputException throws an error if it is a negative number
     * @throws NumberFormatException throws an error if it is not a number
     */
    private static int findIndex(String[] splitInput) throws DukeBadInputException, NumberFormatException {
        if (splitInput.length != 2) {
            throw new DukeBadInputException(
                    String.format("Quack requires exactly one number after the %s command", splitInput[0]));
        }
        int ret = Integer.parseInt(splitInput[1]);
        if (ret < 0) {
            throw new DukeBadInputException(
                    "Quack requires a positive number to help you manage tasks!");
        }
        return ret;
    }

    /**
     * function to find the flags and update both the flags and param field
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

        int[] flagIndex = Parser.find(splitInputs, flags);
        String desc;

        for (int i = 0; i < flagIndex.length - 1; i++) {
            // Check for the presence of the flag
            if (flagIndex[i] == -1) {
                throw new DukeBadInputException(
                        "Quack cant find the required " + flags[i] + " flags, please provide quack with one please");
            }
            if (flagIndex[i + 1] == -1) {
                throw new DukeBadInputException(
                        "Quack cant find the required " + flags[i + 1]
                                + " flags, please provide quack with one please");
            }

            // Check for the description of flag
            String value = String.join(" ", Arrays.copyOfRange(splitInputs, flagIndex[i] + 1, flagIndex[i + 1]));
            if (value.isBlank()) {
                throw new DukeBadInputException(
                        "Please provide quack a description for the " + flags[i] + " flag");
            }

            // check the format of the flag
            LocalDateTime val = LocalDateTime.parse(value, Parser.PARSE_FORMAT);
            flagMap.put(splitInputs[flagIndex[i]], val);
        }
        // Check for a valid description
        desc = String.join(" ", Arrays.copyOfRange(splitInputs, 1, flagIndex[0]));
        if (desc.isBlank()) {
            throw new DukeBadInputException(
                    "Quack doesn't understand an empty description, please provide one!!");
        }
        return desc;
    }

    /**
     * Finds the required flags in the array of strings
     *
     * @param arr   - the array of strings that you want to find the flags from
     * @param items - the array of flags you want to find from the array
     * @return an array of the index of the flags
     * @throws DukeBadInputException - if the flags cannot be found or without a
     *                               description
     */
    private static int[] find(String[] arr, String[] items) throws DukeBadInputException {
        int[] ret = new int[items.length + 1];

        // initialise values, these values will contain the index
        for (int i = 0; i < items.length + 1; i++) {
            // set last item as the length to demarcate the end
            if (i == items.length) {
                ret[i] = arr.length;
                continue;
            }
            ret[i] = -1;
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < items.length; j++) {
                if (arr[i].equals(items[j])) {
                    if (ret[j] != -1) {
                        throw new DukeBadInputException(
                                "There are too many of the " + items[j] + " flag, please just provide one");
                    }
                    ret[j] = i;
                    break;
                }
            }
        }
        return ret;
    }
}
