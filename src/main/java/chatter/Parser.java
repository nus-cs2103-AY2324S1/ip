package chatter;

import chatter.command.AddCommand;
import chatter.command.Command;
import chatter.command.DeleteCommand;
import chatter.command.ExitCommand;
import chatter.command.FindCommand;
import chatter.command.HelpCommand;
import chatter.command.ListCommand;
import chatter.command.MarkCommand;
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
    /**
     * Parses the user's input and returns the corresponding Command to be executed.
     *
     * @param fullCommand Raw string of input from the user.
     * @return Command corresponding to user input to be executed.
     * @throws ChatterException Error thrown if there are invalid fields or inputs.
     */
    public static Command parse(String fullCommand) throws ChatterException {
        switch (fullCommand.split(" ", 2)[0]) {
        case("list"):
            return new ListCommand();
        case("mark"):
            assert (fullCommand.length() > 5);
            return new MarkCommand(true, Character.getNumericValue(fullCommand.charAt(5)));
        case("unmark"):
            assert (fullCommand.length() > 7);
            return new MarkCommand(false, Character.getNumericValue(fullCommand.charAt(7)));
        case("delete"):
            assert (fullCommand.length() > 7);
            return new DeleteCommand(Character.getNumericValue(fullCommand.charAt(7)));
        case("todo"):
            if (fullCommand.length() < 6) {
                throw new ChatterException("☹ OOPS!!! The description of a todo cannot be empty!");
            }
            return new AddCommand(new ToDo(fullCommand.substring(5)));
        case("deadline"):
            int deadlineIndex = fullCommand.indexOf("/by");
            if (deadlineIndex == -1) {
                throw new ChatterException("Please add a '/by' statement with the deadline.");
            }

            assert (deadlineIndex > 0);
            return new AddCommand(new Deadline(fullCommand.substring(9, deadlineIndex - 1),
                    fullCommand.substring(deadlineIndex + 4)));
        case("event"):
            int startIndex = fullCommand.indexOf("/from");
            if (startIndex == -1) {
                throw new ChatterException("Please add a '/from' statement with the start time / date.");
            }

            int endIndex = fullCommand.indexOf("/to");
            if (endIndex == -1) {
                throw new ChatterException("Please add a '/to' statement with the end time / date.");
            }

            assert (startIndex > 0 && endIndex > 0);
            return new AddCommand(new Event(fullCommand.substring(6, startIndex - 1),
                    fullCommand.substring(startIndex + 6, endIndex - 1),
                    fullCommand.substring(endIndex + 4)));
        case("bye"):
            return new ExitCommand();
        case("find"):
            assert (fullCommand.length() > 5);
            return new FindCommand(fullCommand.substring(5));
        case("help"):
            return new HelpCommand();
        default:
            throw new ChatterException("OOPS!!! I'm sorry, but I don't know what that means :-("
                    + "\nPlease enter a valid command!");
        }
    }
}
