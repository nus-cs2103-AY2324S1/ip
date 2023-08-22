package commands;

public enum CommandType {
    MARK, UNMARK, TODO, DEADLINE, EVENT, BYE, LIST;

    public static CommandType getCommandType(String commandStr) throws InvalidCommandException{
        switch (commandStr.toLowerCase()) {
            case "mark":
                return MARK;
            case "unmark":
                return UNMARK;
            case "todo":
                return TODO;
            case "deadline":
                return DEADLINE;
            case "event":
                return EVENT;
            case "bye":
                return BYE;
            case "list":
                return LIST;
            default:
                throw new InvalidCommandException();
        }
    }
}
