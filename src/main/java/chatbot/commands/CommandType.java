package chatbot.commands;

/**
 * enum that represents the different command types.
 *
 * @author Owen Yeo
 */
public enum CommandType {
    BYE("bye"),
    DISPLAY_LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    ADD_TODO("todo"),
    ADD_DEADLINE("deadline"),
    ADD_EVENT("event"),
    DELETE("delete"),
    FIND("find"),
    SHOWERROR(""),
    LIST_DUPLICATE("listduplicate"),
    DELETE_DUPLICATE("deleteduplicate");

    private final String input;

    private CommandType(String input) {
        this.input = input;
    }

    /**
     * Parses the input and returns the appropriate command if the input is
     * valid.
     *
     * @param input User's input
     * @return Command that tells what the chatbot should do.
     */
    public static CommandType parseInput(String input) {
        String[] parts = input.split(" ", 2);

        assert parts.length > 0 : "Input should not be empty";

        for (CommandType commandType: CommandType.values()) {
            if (commandType.input.equals(parts[0])) {
                return commandType;
            }
        }
        return SHOWERROR;
    }
}
