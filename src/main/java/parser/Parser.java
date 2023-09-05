package parser;

import commands.Command;
import commands.DeadlineCommand;
import commands.DeleteCommand;
import commands.EventCommand;
import commands.ExitCommand;
import commands.HelpCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.TodoCommand;
import commands.UnknownCommand;
import commands.UnmarkCommand;

/**
 * This class parses the user input to make sense of user commands.
 * The Parser object then returns Command objects that tell the
 * application what to do.
 */
public class Parser {

    /**
     * Returns a Command object based on the user input.
     *
     * @param userInput
     * @return A subclass of the Command object.
     */
    public Command parse(String userInput) {
        String commandWord;
        String arguments = null;

        if (isSingleWord(userInput)) {
            commandWord = userInput;
        } else {
            String[] arr = userInput.split(" ", 2);
            commandWord = arr[0];
            arguments = arr[1];
        }

        switch (commandWord) {

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case TodoCommand.COMMAND_WORD:
            return prepareTodo(arguments);

        case DeadlineCommand.COMMAND_WORD:
            return prepareDeadline(arguments);

        case EventCommand.COMMAND_WORD:
            return prepareEvent(arguments);

        case MarkCommand.COMMAND_WORD:
            return prepareMark(arguments);

        case UnmarkCommand.COMMAND_WORD:
            return prepareUnmark(arguments);

        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            return new UnknownCommand();

        }
    }

    /**
     * Formats the parameters needed for a ToDo task.
     *
     * @param args The user input.
     * @return A ToDoCommand object.
     */
    public Command prepareTodo(String args) {

        return new TodoCommand(args);
    }

    /**
     * Formats the parameters needed for a Deadline task.
     *
     * @param args The user input.
     * @return A DeadlineCommand object.
     */
    public Command prepareDeadline(String args) {

        String[] arr = args.split("/");
        String description = arr[0];
        String endTime = arr[1];
        return new DeadlineCommand(description, endTime);
    }

    /**
     * Formats the parameters needed for an Event task.
     *
     * @param args The user input.
     * @return An EventCommand object.
     */
    public Command prepareEvent(String args) {

        String[] arr = args.split("/");
        String description = arr[0];
        String startTime = arr[1];
        String endTime = arr[2];
        return new EventCommand(description, startTime, endTime);
    }

    /**
     * Formats the parameters needed to mark a Task as done.
     *
     * @param args The user input.
     * @return A MarkCommand object.
     */
    public Command prepareMark(String args) {
        int taskNum = Integer.parseInt(args);
        return new MarkCommand(taskNum);
    }

    /**
     * Formats the parameters needed to mark a Task as not done.
     *
     * @param args The user input.
     * @return An UnmarkCommand object.
     */
    public Command prepareUnmark(String args) {
        int taskNum = Integer.parseInt(args);
        return new UnmarkCommand(taskNum);
    }

    /**
     * Formats the parameters needed to delete a task.
     *
     * @param args The user input.
     * @return A DeleteCommand object.
     */
    public Command prepareDelete(String args) {
        int taskNum = Integer.parseInt(args);
        return new DeleteCommand(taskNum);
    }

    /**
     * Determines if the input contains only one word, or is more than one word.
     *
     * @param input The user input.
     * @return true if input is one word, false otherwise.
     */
    public boolean isSingleWord(String input) {
        return input.length() == input.trim().length();
    }

}
