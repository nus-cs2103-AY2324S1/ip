package chatter;

import chatter.command.*;
import chatter.task.Deadline;
import chatter.task.Event;
import chatter.task.ToDo;

/**
 * Represents a chatter.Parser class to make sense of the user command.
 *
 * @author Anthony Tamzil
 * @version CS2103T Individual Project AY2023/24 Semester 1
 */
public class Parser {
    /** String containing the raw user input. */
    private String input;
    /** String containing the first word of the user input. */
    private String keyword;

    /**
     * Constructor for chatter.Parser class.
     *
     * @param input User input.
     */
    public Parser(String input) {
        this.input = input;
        this.keyword = input.split(" ", 2)[0];
    }

    public Command parse(String fullCommand) throws ChatterException {
        switch (this.keyword) {
        case("list"):
            return new ListCommand();
        case("mark"):
            return new MarkCommand(true, Character.getNumericValue(this.input.charAt(5)));
        case("unmark"):
            return new MarkCommand(false, Character.getNumericValue(this.input.charAt(7)));
        case("delete"):
            return new DeleteCommand(Character.getNumericValue(this.input.charAt(7)));
        case("todo"):
            if (input.length() < 6) {
                throw new ChatterException("☹ OOPS!!! The description of a todo cannot be empty!");
            }
            return new AddCommand(new ToDo(input.substring(5)));
        case("deadline"):
            int deadlineIndex = input.indexOf("/by");
            if (deadlineIndex == -1) {
                throw new ChatterException("Please add a '/by' statement with the deadline.");
            }

            return new AddCommand(new Deadline(input.substring(9, deadlineIndex - 1),
                    input.substring(deadlineIndex + 4)));
        case("event"):
            int startIndex = input.indexOf("/from");
            if (startIndex == -1) {
                throw new ChatterException("Please add a '/from' statement with the start time / date.");
            }

            int endIndex = input.indexOf("/to");
            if (endIndex == -1) {
                throw new ChatterException("Please add a '/to' statement with the end time / date.");
            }

            return new AddCommand(new Event(input.substring(6, startIndex - 1),
                    input.substring(startIndex + 6, endIndex - 1),
                    input.substring(endIndex + 4)));
        case("bye"):
            return new ExitCommand();
        default:
            throw new ChatterException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(" +
                    "\nPlease enter a valid command!");
        }
    }
}
