package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import instructionstuff.Instruction;
import instructionstuff.InstructionEnum;
import taskstuff.Deadline;
import taskstuff.Event;
import taskstuff.Task;
import taskstuff.Todo;


/**
 * A parser class whose main method is the parse method which
 * helps to parse user input or data  and determine whether the input is valid
 * or not.
 */
public class Parser {

    /** A public final datetime formatter which specifies the user input format for date and time. */
    public static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    /** A public final datetime formatter which specifies the output format for date and time. */
    public static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("d MMM yyyy hh:mm a");

    /**
     * Compiles the given input using the given regex pattern.
     *
     * @param input The input to run regex on.
     * @param regex The pattern to match input against.
     * @return An instance of matcher if regex was successful.
     * @throws DukeException If matching was not successful.
     */
    public static Matcher compileRegex(String input, String regex) throws DukeException {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (!matcher.find()) {
            throw new DukeException("Unrecognized format. Try again");
        }
        return matcher;
    }


    /**
     * Returns an instruction enum representing the instruction in the given matcher.
     *
     * @param matcher The matcher which contains an instruction.
     * @return An instruction enum representing the instruction in the given matcher.
     * @throws DukeException If no valid instruction was found in the matcher.
     */
    public static InstructionEnum parseInstruction(Matcher matcher) throws DukeException {
        InstructionEnum instruction = InstructionEnum.getInstructionEnum(matcher.group("instruction"));
        if (instruction == null) {
            throw new DukeException("Unrecognized instruction. Try again.");
        }
        return instruction;
    }

    /**
     * Parses the given string to DateTime using the given DateTimeFormatter.
     *
     * @param s The string to parse.
     * @param d The DateTime formatter to use to parse.
     * @return An instance of LocalDateTime if parse was successful.
     * @throws DukeException If parse was unsuccessful.
     */
    public static LocalDateTime parseDateTime(String s, DateTimeFormatter d) throws DukeException {
        LocalDateTime l;
        try {
            l = LocalDateTime.parse(s, d);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format. Try again.");
        }
        return l;
    }

    /**
     * Parses the given string to Integer.
     *
     * @param s The string to parse.
     * @return An Integer if parse was successful.
     * @throws DukeException If parse was unsuccessful.
     */
    public static Integer parseInteger(String s) throws DukeException {
        Integer l;
        try {
            l = Integer.parseInt(s);
        } catch (DateTimeParseException e) {
            throw new DukeException("Not a integer. Try again.");
        }
        return l;
    }
    /**
     * Parses a mark instruction.
     *
     * @param matcher The regex matcher containing the instruction.
     * @return An instance of Instruction.Mark.
     * @throws DukeException If parse was unsuccessful.
     */
    public static Instruction parseMark(Matcher matcher) throws DukeException {
        if (matcher.group("description") == null) {
            throw new DukeException("Invalid format for mark. Try again.");
        } else if (matcher.group("description").isBlank()) {
            throw new DukeException("Description of deadline cannot be empty. Try again.");
        } else {
            return new Instruction.Mark(Parser.parseInteger(matcher.group("description").trim()));
        }
    }

    /**
     * Parses an unmark instruction.
     *
     * @param matcher The regex matcher containing the instruction.
     * @return An instance of Instruction.Unmark.
     * @throws DukeException If parse was unsuccessful.
     */
    public static Instruction parseUnmark(Matcher matcher) throws DukeException {
        if (matcher.group("description") == null) {
            throw new DukeException("Invalid format for unmark. Try again.");
        } else if (matcher.group("description").isBlank()) {
            throw new DukeException("Description of unmark cannot be empty. Try again.");
        } else {
            return new Instruction.Unmark(Parser.parseInteger(matcher.group("description").trim()));
        }
    }

    /**
     * Parses a delete instruction.
     *
     * @param matcher The regex matcher containing the instruction.
     * @return An instance of Instruction.Delete.
     * @throws DukeException If parse was unsuccessful.
     */
    public static Instruction parseDelete(Matcher matcher) throws DukeException {
        if (matcher.group("description") == null) {
            throw new DukeException("Invalid format for delete. Try again.");
        } else if (matcher.group("description").isBlank()) {
            throw new DukeException("Description of delete cannot be empty. Try again.");
        } else {
            return new Instruction.Delete(Parser.parseInteger(matcher.group("description").trim()));
        }
    }

    /**
     * Parses a find instruction.
     *
     * @param matcher The regex matcher containing the instruction.
     * @return An instance of Instruction.Find.
     * @throws DukeException If parse was unsuccessful.
     */
    public static Instruction parseFind(Matcher matcher) throws DukeException {
        if (matcher.group("description") == null) {
            throw new DukeException("Invalid format for find. Try again.");
        } else if (matcher.group("description").isBlank()) {
            throw new DukeException("Description of find cannot be empty. Try again.");
        } else {
            return new Instruction.Find(matcher.group("description").trim());
        }
    }

    /**
     * Parses a todo instruction.
     *
     * @param matcher The regex matcher containing the instruction.
     * @return An instance of Todo.
     * @throws DukeException If parse was unsuccessful.
     */
    public static Task parseTodo(Matcher matcher) throws DukeException {
        if (matcher.group("description") == null) {
            throw new DukeException("Invalid format for todo. Try again.");
        } else if (matcher.group("description").isBlank()) {
            throw new DukeException("Description of a todo cannot be empty. Try again.");
        } else {
            return new Todo(matcher.group("description").trim());
        }
    }

    /**
     * Parses a deadline instruction.
     *
     * @param matcher The regex matcher containing the instruction.
     * @return An instance of Deadline.
     * @throws DukeException If parse was unsuccessful.
     */
    public static Task parseDeadline(Matcher matcher, DateTimeFormatter d) throws DukeException {
        if (matcher.group("deadlineDescription") == null) {
            throw new DukeException("Invalid format for deadline. Try again.");
        } else if (matcher.group("deadlineDescription").isBlank()) {
            throw new DukeException("Description of a deadline cannot be empty. Try again.");
        } else {
            if (matcher.group("by") == null || matcher.group("by").isBlank()) {
                throw new DukeException("Insufficient number of arguments for a deadline. Try again.");
            } else {
                return new Deadline(matcher.group("deadlineDescription").trim(),
                        Parser.parseDateTime(matcher.group("by").trim(), d));
            }
        }
    }

    /**
     * Parses an event instruction.
     *
     * @param matcher The regex matcher containing the instruction.
     * @return An instance of Event.
     * @throws DukeException If parse was unsuccessful.
     */
    public static Task parseEvent(Matcher matcher, DateTimeFormatter d) throws DukeException {
        if (matcher.group("eventDescription") == null) {
            throw new DukeException("Invalid format for event. Try again.");
        } else if (matcher.group("eventDescription").isBlank()) {
            throw new DukeException("Description of event cannot be empty. Try again.");
        } else {
            if (matcher.group("from") == null || matcher.group("to") == null
                    || matcher.group("from").isBlank() || matcher.group("to").isBlank()) {
                throw new DukeException("Insufficient number of arguments for an event. Try again.");
            } else {
                return new Event(matcher.group("eventDescription").trim(),
                        Parser.parseDateTime(matcher.group("from").trim(), d),
                        Parser.parseDateTime(matcher.group("to").trim(), d));
            }
        }
    }
    /**
     * Parses the given input and checks if it is valid and if it is
     * returns an instruction corresponding to it.
     *
     * @param userInput The string from user to parse.
     * @return An instance of Instruction if parse was successful and null if not.
     * @throws DukeException If parse was unsuccessful.
     */
    public static Instruction parse(String userInput) throws DukeException {
        String regex = "(?<instruction>[\\w]*)(([ ](?<eventDescription>.*) /from (?<from>.*) /to (?<to>.*))"
                + "|[ ]((?<deadlineDescription>.*) /by (?<by>.*))|(?<description>.*))";

        Matcher matcher = Parser.compileRegex(userInput, regex);
        InstructionEnum instruction = Parser.parseInstruction(matcher);
        switch (instruction) {
        case BYE:
            return new Instruction.Exit();
        case LIST:
            return new Instruction.List();
        case MARK:
            return Parser.parseMark(matcher);
        case UNMARK:
            return Parser.parseUnmark(matcher);
        case DELETE:
            return Parser.parseDelete(matcher);
        case FIND:
            return Parser.parseFind(matcher);
        case TODO:
            return new Instruction.Add(Parser.parseTodo(matcher));
        case DEADLINE:
        case EVENT:
            return new Instruction.Add(Parser.parseEvent(matcher, Parser.INPUT_FORMAT));
        default:
            //program will not reach here.
            assert false;
            throw new DukeException("An unexpected error occurred. Try again.");
        }
    }

    /**
     * Parses a string from the data file and returns the task represented by it.
     *
     * @param data String to parse from data file.
     * @return The task if parse was successful.
     * @throws DukeException DukeException if parse failed.
     */
    public static Task parseData(String data) throws DukeException {
        String regex = "\\[(?<instruction>.)]\\[(?<status>.)](((?<eventDescription>.*)\\(from: (?<from>.*) "
                + "to: (?<to>.*)\\))|((?<deadlineDescription>.*)\\(by: (?<by>.*)\\))|(?<description>.*))";
        Matcher matcher = Parser.compileRegex(data, regex);
        Task task;
        InstructionEnum instruction = Parser.parseInstruction(matcher);
        switch (instruction) {
        case TODO:
            task = Parser.parseTodo(matcher);
            break;
        case DEADLINE:
            task = Parser.parseDeadline(matcher, Parser.OUTPUT_FORMAT);
            break;
        case EVENT:
            task = Parser.parseEvent(matcher, Parser.OUTPUT_FORMAT);
            break;
        default:
            //program will not reach here.
            assert false;
            throw new DukeException("");
        }

        if (matcher.group("status").equals("X")) {
            task.setAsDone();
        } else if (!matcher.group("status").equals(" ")) {
            throw new DukeException("");
        }
        return task;
    }
}
