package Frenchie;

/**
 * This is a Parser object responsible for handling user input.
 * <p>
 *     This parser object interprets the user input based on the Command enums.
 * </p>
 */
public class Parser {
    /**
     * This method takes in the user's input to the chatbot and splits it to return the Command.
     * @param input user input to the Frenchie Chatbot
     * @return respective Command from the input string
     */
    public static Command parseCommand(String input) {

        String[] parts = input.split(" ");
        String command = parts[0];

        switch (command) {
        case "list":
            return Command.list;
        case "mark":
            return Command.mark;
        case "unmark":
            return Command.unmark;
        case "todo":
            return Command.todo;
        case "deadline":
            return Command.deadline;
        case "event":
            return Command.event;
        case "bye":
            return Command.bye;
        case "delete":
            return Command.delete;
        case "find":
            return Command.find;
        case "help":
            return Command.help;
        default:
            return Command.invalid;
        }
    }

    /**
     * This method takes in the user's input to the chatbot and splits it to obtain the details
     * of the task: Task Name, Deadline, StartTime and EndTime if applicable.
     *
     * @param input user input to the Frenchie Chatbot.
     * @return String containing all details of the task.
     */
    public static String parseDetails(String input) {
        String[] parts = input.split(" ", 2);
        return (parts.length > 1) ? parts[1] : "";
    }
}
