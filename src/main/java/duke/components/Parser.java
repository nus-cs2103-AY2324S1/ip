package duke.components;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.FilterCommand;
import duke.command.ModifyCommand;
import duke.command.TagCommand;

/**
 * Class that parses the user input.
 */
public class Parser {
    /**
     * Parses user input and returns the corresponding command.
     * @param input User input to be parses.
     * @return Corresponding Command based on the input.
     * @throws DukeException Invalid input.
     */
    public static Command parse(String input) throws DukeException {
        String[] commandInput = input.split(" ");
        String command = commandInput[0];

        try {
            switch (command) {
            case "bye":
                return parseExitCommand();
            case "list":
                return parseListCommand();
            case "mark":
                return parseMarkCommand(commandInput);
            case "unmark":
                return parseUnmarkCommand(commandInput);
            case "todo":
                return parseTodoCommand(input);
            case "deadline":
                return parseDeadlineCommand(input);
            case "event":
                return parseEventCommand(input);
            case "delete":
                return parseDeleteCommand(commandInput);
            case "find":
                return parseFindCommand(commandInput);
            case "tag":
                return parseTagCommand(commandInput);
            case "untag":
                return parseUntagCommand(commandInput);
            default:
                throw new DukeException("I'm afraid I do not quite understand. Could you kindly repeat it?");
            }
        } catch (Exception ex) {
            throw new DukeException(ex.getMessage());
        }
    }

    private static Command parseExitCommand() {
        return new ExitCommand();
    }

    private static Command parseListCommand() {
        return new ModifyCommand("L");
    }

    private static Command parseMarkCommand(String[] commandInput) {
        String type = "M";
        int index = Integer.parseInt(commandInput[1]);
        return new ModifyCommand(type, index);
    }

    private static Command parseUnmarkCommand(String[] commandInput) {
        String type = "U";
        int index = Integer.parseInt(commandInput[1]);
        return new ModifyCommand(type, index);
    }

    private static Command parseTodoCommand(String input) {
        String type = "T";
        String[] task = getTask(type, input);
        ArrayList<String> tags = new ArrayList<>();
        String[] tagsList = task[1].split(" ");
        if (!tagsList[0].equals("")) {
            tags.addAll(Arrays.asList(tagsList));
        }
        return new AddCommand(type, task[0], tags);
    }

    private static Command parseDeadlineCommand(String input) throws DukeException {
        String type = "D";
        String[] task = getTask(type, input);
        ArrayList<String> tags = new ArrayList<>();
        String[] tagsList = task[2].split(" ");
        if (!tagsList[0].equals("")) {
            tags.addAll(Arrays.asList(tagsList));
        }
        return new AddCommand(type, task[0], parseDateTime(task[1]), tags);
    }

    private static Command parseEventCommand(String input) throws DukeException {
        String type = "E";
        String[] task = getTask(type, input);
        ArrayList<String> tags = new ArrayList<>();
        String[] tagsList = task[1].split(" ");
        if (!tagsList[3].equals("")) {
            tags.addAll(Arrays.asList(tagsList));
        }
        return new AddCommand(type, task[0], parseDateTime(task[1]), parseDateTime(task[2]), tags);
    }

    private static Command parseDeleteCommand(String[] commandInput) {
        String type = "D";
        int index = Integer.parseInt(commandInput[1]);
        return new ModifyCommand(type, index);
    }

    private static Command parseFindCommand(String[] commandInput) {
        String type = "F";
        String keyword = commandInput[1];
        return new FilterCommand(type, keyword);
    }

    private static Command parseTagCommand(String[] commandInput) {
        String type = "T";
        int index = Integer.parseInt(commandInput[1]);
        String [] tags = new String[commandInput.length - 2]; // Trims the command and index.
        for (int i = 0; i < tags.length; i++) {
            tags[i] = commandInput[i + 2]; // Shift down by 2 for the command and index
        }
        return new TagCommand(type, index, tags);
    }

    private static Command parseUntagCommand(String[] commandInput) {
        String type = "U";
        int index = Integer.parseInt(commandInput[1]);
        String [] tags = new String[commandInput.length - 2]; // Trims the command and index.
        for (int i = 0; i < tags.length; i++) {
            tags[i] = commandInput[i + 2]; // Shift down by 2 for the command and index
        }
        return new TagCommand(type, index, tags);
    }

    /**
     * Parses the input further if the command is to add a new task.
     *
     * @param type Type of Command, Todo, Deadline or Event.
     * @param input User input, containing details of task.
     * @return String array containing the details of the task, parsed into the correct indexes.
     */
    public static String[] getTask(String type, String input) {
        switch (type) {
        case "T": {
            String[] description = input.substring(5).split(" /tag ");
            String name = description[0];
            if (description.length == 1) {
                return new String[]{name, ""};
            }
            return description;
        }
        case "D": {
            String[] deadline = input.substring(9).split(" /by ");
            String name = deadline[0];
            String[] byTags = deadline[1].split(" /tag ");
            String by = byTags[0];
            if (byTags.length == 1) {
                return new String[]{name, by, ""};
            }
            String tags = byTags[1];
            return new String[]{name, by, tags};
        }
        case "E": {
            String details = input.substring(6);
            String[] taskStartEnd = details.split(" /from ");
            String name = taskStartEnd[0];
            String[] startEnd = taskStartEnd[1].split(" /to ");
            String start = startEnd[0];
            String[] endTags = startEnd[1].split(" /tag ");
            String end = endTags[0];
            if (endTags.length == 1) {
                return new String[]{name, start, end, ""};
            }
            String tags = endTags[1];
            return new String[]{name, start, end, tags};
        }
        default:
            return new String[]{};
        }
    }

    /**
     * Parses the input further if the input contains the String for dateTime.
     *
     * @param dateTimeStr String of the dateTime to be converted.
     * @return LocalDateTime object to be used to construct the Task.
     * @throws DukeException Invalid format or input for dateTimeStr.
     */
    public static LocalDateTime parseDateTime(String dateTimeStr) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (DateTimeParseException ex) {
            throw new DukeException("I'm afraid I do not quite understand. Please input the date and time"
                    + "as follows:\nd/M/yyyy HHmm");
        }
    }
}
