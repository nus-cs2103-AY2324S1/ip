package duchess.command;

/**
 * A Command class.
 */
public abstract class Command implements Executable {

    /**
     * Factory method to get Commands.
     */
    public static Command getCommand(CommandType commandType) {
        switch(commandType) {
        case LIST:
            return new ListTaskCommand();
        case MARK:
            return new MarkTaskCommand();
        case UNMARK:
            return new UnmarkTaskCommand();
        case DELETE:
            return new DeleteTaskCommand();
        case SEARCH:
            return new SearchTaskCommand();
        case TODO:
            return new AddToDoCommand();
        case DEADLINE:
            return new AddDeadlineCommand();
        case EVENT:
            return new AddEventCommand();
        case ADD_TAG:
            return new AddTagCommand();
        case DELETE_TAG:
            return new DeleteTagCommand();
        default:
            return new UnrecognizedCommand();
        }
    }
}
