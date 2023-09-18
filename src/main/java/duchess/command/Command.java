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
        }
        return null;
    }
}
