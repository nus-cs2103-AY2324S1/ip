package chatbot.parser;

import chatbot.commands.AddDeadline;
import chatbot.commands.AddEvent;
import chatbot.commands.AddToDo;
import chatbot.commands.Bye;
import chatbot.commands.Command;
import chatbot.commands.CommandType;
import chatbot.commands.DeleteItem;
import chatbot.commands.DisplayList;
import chatbot.commands.FindTask;
import chatbot.commands.MarkItem;
import chatbot.commands.ShowError;
import chatbot.commands.UnmarkItem;
import chatbot.exceptions.InvalidCommandException;
import chatbot.exceptions.InvalidDescriptionException;
import chatbot.exceptions.InvalidIndexException;

/**
 * Parser that parses the inputs from the user and inteprets it.
 *
 * @author Owen Yeo
 */
public class Parser {

    /**
     * Parses the input from the user and outputs a command for
     * the chatbot to execute.
     *
     * @param input String input from user
     * @return Command command depending on input.
     * @throws InvalidDescriptionException Exception thrown when an invalid command description is passed.
     * @throws InvalidCommandException Exception thrown when an invalid command is passed
     * @throws InvalidIndexException Exception thrown when an invalid index is passed.
     */
    public static Command parse(String input) throws
            InvalidDescriptionException, InvalidCommandException, InvalidIndexException {
        //Split the input so that we can read command and their description (if any).
        String[] inputStrings = input.split(" ", 2);
        assert inputStrings.length > 0 : "Input should not be empty";

        CommandType command = CommandType.parseInput(inputStrings[0]);

        switch(command) {
        case BYE:
            return new Bye("", CommandType.BYE);

        case DISPLAY_LIST:
            return new DisplayList("", CommandType.DISPLAY_LIST);

        case MARK:
            return new MarkItem(inputStrings[1], CommandType.MARK);

        case UNMARK:
            return new UnmarkItem(inputStrings[1], CommandType.UNMARK);

        case ADD_TODO:
            return new AddToDo(inputStrings[1], CommandType.ADD_TODO);

        case ADD_DEADLINE:
            return new AddDeadline(inputStrings[1], CommandType.ADD_DEADLINE);

        case ADD_EVENT:
            return new AddEvent(inputStrings[1], CommandType.ADD_EVENT);

        case DELETE:
            return new DeleteItem(inputStrings[1], CommandType.DELETE);

        case FIND:
            return new FindTask(inputStrings[1], CommandType.FIND);

        default:
            return new ShowError("", CommandType.SHOWERROR,
                    new InvalidCommandException("Don't be stupid, speak english."));
        }
    }
}
