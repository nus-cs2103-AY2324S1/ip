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
        if (input.trim().equals("list")) {
            return Command.LIST;
        } else if (input.trim().equals("bye")) {
            return Command.BYE;
        } else if (input.startsWith("mark")) {
            return Command.MARK;
        } else if (input.startsWith("unmark")) {
            return Command.UNMARK;
        } else if (input.startsWith("todo")) {
            return Command.TODO;
        } else if (input.startsWith("deadline")) {
            return Command.DEADLINE;
        } else if (input.startsWith("event")) {
            return Command.EVENT;
        } else if (input.startsWith("delete")) {
            return Command.DELETE;
        } else if (input.startsWith("find")) {
            return Command.FIND;
        } else if (input.startsWith("tag")) {
            return Command.TAG;
        } else if (input.trim().equals("showtags")) {
            return Command.SHOWTAGS;
        } else if (input.startsWith("removetag")) {
            return Command.REMOVETAG;
        }
        throw new InvalidInputException();
    }

}
