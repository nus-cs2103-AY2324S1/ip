package mypackage;

public enum CommandType {
    BYE, 
    LIST, 
    MARK, 
    UNMARK, 
    TODO, 
    DEADLINE, 
    EVENT, 
    DELETE, 
    UNKNOWN;

    public static CommandType getCommandType(String command) {
        if (command.equals("bye")) {
            return BYE;
        } else if (command.equals("list")) {
            return LIST;
        } else if (command.startsWith("mark")) {
            return MARK;
        } else if (command.startsWith("unmark")) {
            return UNMARK;
        } else if (command.startsWith("todo")) {
            return TODO;
        } else if (command.startsWith("deadline")) {
            return DEADLINE;
        } else if (command.startsWith("event")) {
            return EVENT;
        } else if (command.startsWith("delete")) {
            return DELETE;
        } else {
            return UNKNOWN;
        }
    }
}
