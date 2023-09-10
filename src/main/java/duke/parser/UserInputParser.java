package duke.parser;

import java.util.regex.Pattern;

import duke.Action;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidIndexException;
import duke.exception.InvalidInputException;
import duke.message.ByeMessage;
import duke.message.MenuMessage;
import duke.message.Message;
import duke.task.DeadlinesTask;
import duke.task.EventsTask;
import duke.task.TaskList;
import duke.task.TodoTask;
import duke.templates.MessageTemplates;

/**
 * Represents the UserInputParser.
 */
public class UserInputParser {
    private static boolean isActive = true;

    /**
     * Returns the Action of the user input.
     * @param userInput User input.
     * @return Action of the user input.
     * @throws InvalidInputException If user input is invalid.
     * @throws InvalidCommandException If command is invalid.
     */
    private static Action getAction(String userInput) throws InvalidInputException, InvalidCommandException {
        if (userInput.equals("bye")) {
            return Action.BYE;
        }
        if (userInput.equals("list")) {
            return Action.LIST;
        }
        if (Pattern.matches("mark \\d+", userInput)) {
            return Action.MARK;
        }
        if (Pattern.matches("unmark \\d+", userInput)) {
            return Action.UNMARK;
        }
        // Redundant check, to satisfy Level-5: Handle Errors requirement
        if (Pattern.matches("^todo\\s*$", userInput)) {
            throw new InvalidInputException(MessageTemplates.MESSAGE_INVALID_TODO);
        }
        if (Pattern.matches("todo .+", userInput)) {
            return Action.TODO;
        }
        if (Pattern.matches("deadline .+ /by .+", userInput)) {
            return Action.DEADLINE;
        }
        if (Pattern.matches("event .+ /from .+ /to .+", userInput)) {
            return Action.EVENT;
        }
        if (Pattern.matches("delete \\d+", userInput)) {
            return Action.DELETE;
        }
        if (Pattern.matches("find .+", userInput)) {
            return Action.FIND;
        }
        if (userInput.equals("/help")) {
            return Action.HELP;
        }
        throw new InvalidCommandException();
    }

    /**
     * Parses the user input.
     * @param userInput User input.
     * @param taskList TaskList.
     * @return Message.
     * @throws InvalidInputException If user input is invalid.
     * @throws InvalidCommandException Command does not exist.
     * @throws InvalidIndexException If index is invalid.
     */
    public static Message parse(String userInput, TaskList taskList)
            throws InvalidInputException, InvalidCommandException, InvalidIndexException {
        Action action = UserInputParser.getAction(userInput);
        int num;
        String name;
        switch (action) {
        case BYE:
            isActive = false;
            return new ByeMessage();
        case LIST:
            return taskList.printList();
        case MARK:
            num = Integer.parseInt(userInput.split(" ", 2)[1]);
            return taskList.markTask(num);
        case UNMARK:
            num = Integer.parseInt(userInput.split(" ", 2)[1]);
            return taskList.unmarkTask(num);
        case TODO:
            name = userInput.split(" ", 2)[1]; // separate command from name
            return taskList.add(new TodoTask(name, false));
        case DEADLINE:
            String[] nameAndDeadline = userInput.split(" /by ", 2);

            if (nameAndDeadline[1].contains("/by")) {
                throw new InvalidInputException(MessageTemplates.MESSAGE_DEADLINE_CONTAINS_BY);
            }
            assert !nameAndDeadline[1].contains("/by") : "deadline name should not contain ' /by '";

            name = nameAndDeadline[0].split(" ", 2)[1]; // separate command from name
            String by = DateTimeParser.parseDateTime(nameAndDeadline[1]);
            return taskList.add(new DeadlinesTask(name, false, by));
        case EVENT:
            String[] nameFromAndTo = userInput.split(" /to ", 2);

            if (nameFromAndTo[1].contains("/to")) {
                throw new InvalidInputException(MessageTemplates.MESSAGE_EVENT_CONTAINS_TO);
            }
            assert !nameFromAndTo[1].contains("/to") : "event name and from date should not contain ' /to '";

            String[] nameAndFrom = nameFromAndTo[0].split(" /from ", 2);

            if (nameAndFrom[1].contains("/from")) {
                throw new InvalidInputException(MessageTemplates.MESSAGE_EVENT_CONTAINS_FROM);
            }
            assert !nameAndFrom[1].contains("/from") : "event name should not contain ' /from '";

            name = nameAndFrom[0].split(" ", 2)[1]; // separate command from name
            String from = DateTimeParser.parseDateTime(nameAndFrom[1]);
            String to = DateTimeParser.parseDateTime(nameFromAndTo[1]);

            if (!DateTimeParser.isValidPeriod(from, to)) {
                throw new InvalidInputException(MessageTemplates.MESSAGE_INVALID_EVENT_PERIOD);
            }
            return taskList.add(new EventsTask(name, false, from, to));
        case DELETE:
            num = Integer.parseInt(userInput.split(" ", 2)[1]);
            return taskList.delete(num);
        case FIND:
            name = userInput.split(" ", 2)[1];
            return taskList.find(name);
        case HELP:
            return new MenuMessage();
        default:
            throw new InvalidCommandException();
        }
    }
    /**
     * Returns the isActive.
     * @return isActive.
     */
    public static boolean getIsActive() {
        return UserInputParser.isActive;
    }
    /**
     * Sets the isActive.
     *
     * @param isActive isActive.
     */
    public static void setIsActive(boolean isActive) {
        UserInputParser.isActive = isActive;
    }
}
