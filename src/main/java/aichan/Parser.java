package aichan;

import aichan.command.AddDeadlineCommand;
import aichan.command.AddEventCommand;
import aichan.command.AddToDoCommand;
import aichan.command.Command;
import aichan.command.DeleteCommand;
import aichan.command.ExitCommand;
import aichan.command.FindCommand;
import aichan.command.ListCommand;
import aichan.command.MarkCommand;
import aichan.command.UnmarkCommand;

/**
 * Represents a parser that makes sense of the user command.
 */
public class Parser {

    /**
     * Parses user's input and returns the respective Command object.
     *
     * @param input Input entered by the user.
     * @return An instance of Command class or its subclass.
     * @throws AiChanException If the input has incorrect format.
     */
    public static Command parse(String input) throws AiChanException {
        // command is user's input
        // put the if-else logic to call different constructor (subclass)
        if (input == null ) {
            throw new AiChanException("Please enter command, thanks!");
        }
        String inputs[] = input.split(" ", 2);

        String commandType = inputs[0];
        if (commandType.equals(ActionType.BYE.toString())) {
            return new ExitCommand();
        } else if (commandType.equals(ActionType.LIST.toString())) {
            return new ListCommand();
        } else if (commandType.equals(ActionType.MARK.toString())) {
            return new MarkCommand(strToNum(checkLen(inputs)));
        } else if (commandType.equals(ActionType.UNMARK.toString())) {
            return new UnmarkCommand(strToNum(checkLen(inputs)));
        } else if (commandType.equals(ActionType.TODO.toString())) {
            return new AddToDoCommand(checkLen(inputs));
        } else if (commandType.equals(ActionType.DEADLINE.toString())) {
            return new AddDeadlineCommand(checkLen(inputs));
        } else if (commandType.equals(ActionType.EVENT.toString())) {
            return new AddEventCommand(checkLen(inputs));
        } else if (commandType.equals(ActionType.DELETE.toString())) {
            return new DeleteCommand(strToNum(checkLen(inputs)));
        } else if (commandType.equals(ActionType.FIND.toString())) {
            return new FindCommand(checkLen(inputs));
        } else {
            throw new AiChanException("oops~ I'm so sorry, but I don't know what that means :'(");
        }
    }

    /**
     * Checks whether the input contains description and returns the description.
     *
     * @param inputs String array which supposed to have command type as first element, description as second element.
     * @return String contains the description.
     * @throws AiChanException If the description is missing.
     */
    public static String checkLen(String[] inputs) throws AiChanException {
        if (inputs.length == 1 || inputs[1].length() == 0) {
            throw new AiChanException("oops~ The description of a " + inputs[0] + " cannot be empty.");
        }
        return inputs[1];
    }

    /**
     * Converts a string to number.
     *
     * @param intStr String contains number.
     * @return The corresponding number.
     * @throws AiChanException If the string contains any numeric character.
     */
    public static int strToNum(String intStr) throws AiChanException {
        try {
            return Integer.parseInt(intStr);
        } catch (NumberFormatException e) {
            throw new AiChanException("Please provide a task number.");
        }
    }
}

