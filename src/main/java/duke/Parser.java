package duke;

import instructionstuff.Instruction;
import instructionstuff.InstructionEnum;

import taskstuff.Event;
import taskstuff.Task;
import taskstuff.Todo;
import taskstuff.Deadline;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.time.LocalDateTime;


/**
 * A parser class whose main method is the parse method which
 * helps to parse user input or data  and determine whether the input is valid
 * or not.
 */
public class Parser {

    public static DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    public static DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("d MMM yyyy hh:mm a");


    /**
     * Parses the given input and checks if it is valid and if it is
     * returns an instruction corresponding to it.
     *
     * @param userInput The string from user to parse.
     * @return Returns an instance of instruction.Instruction if parse was successful and null if not.
     */
    public static Instruction parse(String userInput) throws DukeException {

        String regex = "^([\\w]*)(([ ](.*) /from (.*) /to (.*))|[ ]((.*) /by (.*))|(.*))";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(userInput);
        if (!matcher.find()) {
            throw new DukeException("Unrecognized format. Try again");
        }

        InstructionEnum instruction = InstructionEnum.getInstructionEnum(matcher.group(1));
        if (instruction == null) {
            throw new DukeException("Unrecognized instruction. Try again.");
        }
        switch (instruction) {
        case BYE:
            return new Instruction.Exit();
        case LIST:
            if (matcher.group(10) == null || !matcher.group(10).isBlank()) {
                throw new DukeException("The description of list must be empty. Try again.");
            } else {
                return new Instruction.List();
            }
        case MARK:
            if (matcher.group(10) == null || matcher.group(10).isBlank()) {
                throw new DukeException("The description of mark cannot be empty. Try again.");
            } else {
                try {
                    Integer index = Integer.parseInt(matcher.group(10).trim());
                    return new Instruction.Mark(index);

                } catch (NumberFormatException n) {
                    throw new DukeException("The index is not a valid index. Try again.");
                }
            }
        case UNMARK:
            if (matcher.group(10) == null || matcher.group(10).isBlank()) {
                throw new DukeException("The description of unmark cannot be empty. Try again.");
            } else {
                try {
                    Integer index = Integer.parseInt(matcher.group(10).trim());
                    return new Instruction.Unmark(index);

                } catch (NumberFormatException n) {
                    throw new DukeException("The index is not a valid index. Try again.");
                }
            }
        case DELETE:
            if (matcher.group(10) == null || matcher.group(10).isBlank()) {
                throw new DukeException("The description of delete cannot be empty. Try again.");
            } else {
                try {
                    Integer index = Integer.parseInt(matcher.group(10).trim());
                    return new Instruction.Delete(index);

                } catch (NumberFormatException n) {
                    throw new DukeException("The index is not a valid index. Try again.");
                }
            }
        case TODO:
            if(matcher.group(10) == null || matcher.group(10).isBlank()){
                throw new DukeException("The description of a todo cannot be empty. Try again.");
            } else {
                return new Instruction.Add(new Todo(matcher.group(10).trim()));
            }
        case DEADLINE:
            if (matcher.group(8) == null || matcher.group(8).isBlank()) {
                throw new DukeException("The description of a deadline cannot be empty. Try again.");
            } else {
                if (matcher.group(9) == null || matcher.group(9).isBlank()) {
                    throw new DukeException("Insufficient number of arguments for a deadline. Try again.");
                } else {
                    LocalDateTime dateTime;
                    try {
                        dateTime = LocalDateTime.parse(matcher.group(9).trim(), Parser.inputFormat);
                    } catch (DateTimeParseException e) {
                        throw new DukeException("Invalid date format. Try again.");
                    }

                    return new Instruction.Add(new Deadline(matcher.group(8).trim(), dateTime));
                }
            }
        case EVENT:
            if (matcher.group(4) == null || matcher.group(4).isBlank()) {
                throw new DukeException("The description of an event cannot be empty. Try again.");
            } else {
                if (matcher.group(5) == null || matcher.group(6) == null
                        || matcher.group(5).isBlank() || matcher.group(6).isBlank()) {
                    throw new DukeException("Insufficient number of arguments for an event. Try again.");
                } else {
                    LocalDateTime startDateTime, endDateTime;
                    try {
                        startDateTime = LocalDateTime.parse(matcher.group(5).trim(), Parser.inputFormat);
                        endDateTime = LocalDateTime.parse(matcher.group(6).trim(), Parser.inputFormat);
                    } catch (DateTimeParseException e) {
                        throw new DukeException("Invalid date format. Try again. ");
                    }
                    return new Instruction.Add(new Event(matcher.group(4).trim(), startDateTime,
                            endDateTime));
                }
            }
        default:
            //program will not reach here.
            throw new DukeException("An unexpected error occurred. Try again.");
        }
    }

    /**
     * Parses a string from the data file and returns the task represented by it.
     *
     * @param data String to parse from data file.
     * @return returns the task if parse was successful.
     * @throws DukeException Throws duke.DukeException if parse failed.
     */
    public static Task parseData(String data) throws DukeException {
        String regex = "\\[(.)]\\[(.)](((.*)\\(from: (.*) to: (.*)\\))|((.*)\\(by: (.*)\\))|(.*))";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);
        if (!matcher.find()) {
            throw new DukeException("");
        }
        Task task;
        InstructionEnum instruction = InstructionEnum.getInstructionEnum(matcher.group(1));
        if (instruction == null) {
            throw new DukeException("");
        }
        switch (instruction) {
        case TODO:
            if(matcher.group(11) == null || matcher.group(11).isBlank()){
                throw new DukeException("");
            } else {
                task = new Todo(matcher.group(11).trim());
            }
            break;
        case DEADLINE:
            if (matcher.group(10) == null || matcher.group(10).isBlank() ||
                    matcher.group(9) == null || matcher.group(9).isBlank()) {
                throw new DukeException("");
            } else {
                LocalDateTime dateTime;
                try {
                    dateTime = LocalDateTime.parse(matcher.group(10), Parser.outputFormat);
                } catch (DateTimeParseException e) {
                    throw new DukeException("");
                }
                task = new Deadline(matcher.group(9).trim(), dateTime);
            }
            break;
        case EVENT:
            if (matcher.group(5) == null || matcher.group(5).isBlank() || matcher.group(6) == null ||
                    matcher.group(7) == null || matcher.group(6).isBlank() || matcher.group(7).isBlank()) {
                throw new DukeException("");
            } else {
                LocalDateTime startDateTime;
                LocalDateTime endDateTime;
                try {
                    startDateTime = LocalDateTime.parse(matcher.group(6), Parser.outputFormat);
                    endDateTime = LocalDateTime.parse(matcher.group(7), Parser.outputFormat);
                } catch (DateTimeParseException e) {
                    throw new DukeException("");
                }
                task = new Event(matcher.group(5).trim(), startDateTime, endDateTime);
            }
            break;
        default:
            //program will not reach here.
            return null;
        }

        if (matcher.group(2).equals("X")) {
            task.setAsDone();
        } else if (!matcher.group(2).equals(" ")) {
            throw new DukeException("");
        }
        return task;

    }
}
