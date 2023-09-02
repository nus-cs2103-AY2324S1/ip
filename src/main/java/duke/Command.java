package duke;

/**
 * This class is used to represent the different commands that can be given to the chatbot
 */
public enum Command {
    LIST,
    MARK,
    UNMARK,
    BYE,
    TODO,
    DEADLINE,
    EVENT,
    DELETE,
    FIND;

    /**
     * This array is used to represent the valid commands that can be given to the chatbot
     */
    public static final String[] VALID_COMMANDS = {"list", "bye", "todo <task_name>",
        "deadline <task_name> /by <deadline>", "event <task_name> /from <start-time> /to <end-time>",
        "mark <number>", "unmark <number>", "delete <number>", "find <keyword>"
    };

    /**
     * This method is used to get the command from the user's input
     * @param input the user's input
     * @return  the command
     * @throws InvalidInputException    if the input is invalid
     */
    public static Command getCommand(String input) throws InvalidInputException {
        String[] inputtedWords = input.split(" ");
        // Command word should always be first word without space
        String commandWord = inputtedWords[0];
        if (commandWord.equals("list")) {
            return Command.LIST;
        } else if (commandWord.equals("bye")) {
            return Command.BYE;
        } else if (commandWord.startsWith("mark")) {
            return Command.MARK;
        } else if (commandWord.startsWith("unmark")) {
            return Command.UNMARK;
        } else if (commandWord.startsWith("todo")) {
            return Command.TODO;
        } else if (commandWord.startsWith("deadline")) {
            return Command.DEADLINE;
        } else if (commandWord.startsWith("event")) {
            return Command.EVENT;
        } else if (commandWord.startsWith("delete")) {
            return Command.DELETE;
        } else if (commandWord.startsWith("find")) {
            return Command.FIND;
        } else {
            throw new InvalidInputException();
        }
    }

}
