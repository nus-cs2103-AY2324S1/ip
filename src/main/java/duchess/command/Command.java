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
        }
        return null;
    }
}
