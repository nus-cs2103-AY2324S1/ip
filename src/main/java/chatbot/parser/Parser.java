package chatbot.parser;

import chatbot.commands.Command;
import chatbot.commands.AddDeadline;
import chatbot.commands.AddEvent;
import chatbot.commands.AddToDo;
import chatbot.commands.Bye;
import chatbot.commands.CommandType;
import chatbot.commands.DeleteItem;
import chatbot.commands.DisplayList;
import chatbot.commands.MarkItem;
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
        //Split the input so that we can read integers.
        String[] inputStrings = input.split(" ", 2);
        CommandType command = CommandType.parseInput(inputStrings[0]);
        if (command == null) {
            throw new InvalidCommandException("What are you saying? Try again.");
        }

        switch(command) {
            case BYE:
                return new Bye("");

            case DISPLAY_LIST:
                return new DisplayList("");

            case MARK:
                return new MarkItem(inputStrings[1]);

            case UNMARK:
                return new UnmarkItem(inputStrings[1]);

            case ADD_TODO:
                return new AddToDo(inputStrings[1]);

            case ADD_DEADLINE:
                return new AddDeadline(inputStrings[1]);
            
            case ADD_EVENT:
                return new AddEvent(inputStrings[1]);

            case DELETE:
                return new DeleteItem(inputStrings[1]);

            default:
                throw new InvalidCommandException("Don't be stupid, speak english.");
        }
    }
}
