package extensions;

/**
 * Represents a fixed set of input command strings which can be assigned to the
 * Command variable.
 */
public enum Command {
    SHOWTASKS("list"),
    MARKTASKASDONE("mark"),
    MARKTASKASNOTDONE("unmark"),
    ADDTODO("todo"),
    ADDDEADLINE("deadline"),
    ADDEVENT("event"),
    DELETETASK("delete");
    private String command;
    private Command(String command) {
        this.command = command;
    }
    public static Command getCommand(String inputCommand) {
        for (Command command : Command.values()) {
            if (command.command.equals(inputCommand)) return command;
        }
        return null;
    }
}