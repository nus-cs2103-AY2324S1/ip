package command;

public enum CommandKeyword {

    TODO, DEADLINE, EVENT, EXIT, DELETE, FIND, HELP, LIST, MARK, UNMARK, PRIORITY, INVALID;

    public static CommandKeyword of(String s) {
        switch (s) {

        case "todo":
            return TODO;

        case "deadline":
            return DEADLINE;

        case "event":
            return EVENT;

        case "bye":
            return EXIT;

        case "delete":
            return DELETE;

        case "find":
            return FIND;

        case "help":
            return HELP;

        case "list":
            return LIST;

        case "mark":
            return MARK;

        case "unmark":
            return UNMARK;

        case "priority":
            return PRIORITY;

        default:
            return INVALID;
        }
    }

}
