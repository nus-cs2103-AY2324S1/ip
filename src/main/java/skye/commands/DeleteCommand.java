package skye.commands;

/**
 * Represents a generic delete command to be implemented by its subclasses.
 */
public abstract class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    private final int index;

    /**
     * Instantiates the delete command for deleting resources
     *
     * @param index Index number on the list
     */
    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
