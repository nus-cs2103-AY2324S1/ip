package skye.commands;

/**
 * Represents a generic find command to be implemented by its subclasses.
 */
public abstract class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }
}
