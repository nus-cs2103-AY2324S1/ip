package duke;

/**
 * This class is used to represent the different commands that can be given to the chatbot
 */
public enum Command {
    LIST,
    SHOWTAGS,
    MARK,
    UNMARK,
    BYE,
    TODO,
    DEADLINE,
    EVENT,
    TAG,
    REMOVETAG,
    DELETE,
    FIND;

    /**
     * This array is used to represent the valid commands that can be given to the chatbot
     */
    public static final String[] VALID_COMMANDS = {"list", "bye", "todo <task_name>",
        "deadline <task_name> /by <deadline>", "event <task_name> /from <start-time> /to <end-time>",
        "mark <number>", "unmark <number>", "delete <number>", "find <keyword>", "tag <number> <tag>",
        "showtags", "removetag <number> <tag>"
    };

    /**
     * This method is used to get the command from the user's input
     * @param input the user's input
     * @return the command
     * @throws InvalidInputException if the input is invalid
     */
    public static Command getCommand(String input) throws InvalidInputException {
        String commandWord = input.split(" ")[0];
        if (input.trim().equals("list")) {
            return Command.LIST;
        } else if (input.trim().equals("bye")) {
            return Command.BYE;
        } else if (commandWord.equals("mark")) {
            return Command.MARK;
        } else if (commandWord.equals("unmark")) {
            return Command.UNMARK;
        } else if (commandWord.equals("todo")) {
            return Command.TODO;
        } else if (commandWord.equals("deadline")) {
            return Command.DEADLINE;
        } else if (commandWord.equals("event")) {
            return Command.EVENT;
        } else if (commandWord.equals("delete")) {
            return Command.DELETE;
        } else if (commandWord.equals("find")) {
            return Command.FIND;
        } else if (commandWord.equals("tag")) {
            return Command.TAG;
        } else if (input.trim().equals("showtags")) {
            return Command.SHOWTAGS;
        } else if (commandWord.equals("removetag")) {
            return Command.REMOVETAG;
        }
        throw new InvalidInputException();
    }

}
