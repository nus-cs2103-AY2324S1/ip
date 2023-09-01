package chatbot;

import chatbot.command.*;

/**
 * Class which deals with making sense of the user command.
 */
public class Parser {

    /**
     * Enumeration representing different types of tasks and commands.
     */
    public enum TaskType {
        TODO, DEADLINE, EVENT, MARK, UNMARK, UNKNOWN, BYE;
    }

    /**
     * method to determine types of the task.
     *
     * @param userInput user command
     * @return Task type of the command
     */
    public static TaskType determineTaskType(String userInput) {
        if (userInput.startsWith("todo")) return TaskType.TODO;
        if (userInput.startsWith("deadline")) return TaskType.DEADLINE;
        if (userInput.startsWith("event")) return TaskType.EVENT;
        if (userInput.startsWith("mark")) return TaskType.MARK;
        if (userInput.startsWith("unmark")) return TaskType.UNMARK;
        if ("bye".equalsIgnoreCase(userInput)) return TaskType.BYE;
        return TaskType.UNKNOWN;
    }

    /**
     * Parse the user command which its task type is todo.
     *
     * @param userInput user command
     * @return array of string after parsing the command
     * @throws ChatbotException if the description is empty
     */
    public static String[] parseTodo(String userInput) throws ChatbotException {
        if (userInput.length() <= 4) {
            throw new ChatbotException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        String taskDescription = userInput.substring(5);
        return new String[]{taskDescription};
    }

    /**
     * Parse the user command which its task type is DEADLINE.
     *
     * @param userInput user command
     * @return array of string after parsing the command
     * @throws ChatbotException if the description or date is empty
     */
    public static String[] parseDeadline(String userInput) throws ChatbotException {
        String[] parts = userInput.substring(9).split("/by");
        if (parts.length < 2) {
            throw new ChatbotException("☹ OOPS!!! The date for a deadline cannot be empty.");
        }
        String taskDescription = parts[0].trim();
        String date = parts[1].trim();

        if (taskDescription.isEmpty()) {
            throw new ChatbotException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }

        if (date.isEmpty()) {
            throw new ChatbotException("☹ OOPS!!! The date for a deadline cannot be empty.");
        }
        return parts;
    }

    /**
     * Parse the user command which its task type is EVENT.
     *
     * @param userInput user command
     * @return array of string after parsing the command
     * @throws ChatbotException if the date is empty
     */
    public static String[] parseEvent(String userInput) throws ChatbotException {
        String[] parts = userInput.substring(6).split("/from|/to");
        if (parts.length < 3) {
            throw new ChatbotException("☹ OOPS!!! The start or end date for an event cannot be empty.");
        }
        return parts;
    }

    /**
     * Parse the user command which its task type is MARK.
     *
     * @param userInput user command
     * @return array of string after parsing the command
     */
    public static int parseMark(String userInput) {
        return Integer.parseInt(userInput.split(" ")[1]);
    }

    /**
     * Parse the user command which its task type is UNKNOWN.
     *
     * @param userInput user command
     * @return array of string after parsing the command
     * @throws ChatbotException when we could not identify the case
     */
    public static Command parseCommand(String userInput) throws ChatbotException {
        TaskType taskType = determineTaskType(userInput);
        Command command;

        switch (taskType) {
            case TODO:
                String[] todoInput = parseTodo(userInput);
                command = new AddTodoCommand(todoInput[0].trim());
                break;
            case DEADLINE:
                String[] deadlineInput = parseDeadline(userInput);
                command = new AddDeadlineCommand(deadlineInput[0].trim(), deadlineInput[1].trim());
                break;
            case EVENT:
                String[] eventInput = parseEvent(userInput);
                command = new AddEventCommand(eventInput[0].trim(), eventInput[1].trim(), eventInput[2].trim());
                break;
            case MARK:
                int markInt = parseMark(userInput);
                command = new MarkCommand(markInt);
                break;
            case UNMARK:
                int unmarkInt = parseMark(userInput);
                command = new UnmarkCommand(unmarkInt);
                break;
            case BYE:
                command = new ExitCommand();
                break;
            default:
                command = new UnknownCommand(userInput);
                break;
        }

        return command;
    }


}
