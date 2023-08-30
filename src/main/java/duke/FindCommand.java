package duke;

/**
 * Encapsulates a Find Command
 */
public class FindCommand extends Command {
    private String query;

    /**
     * Creates a find command
     * @param query Query string
     */
    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public void execute(ChatBotList list, Ui ui, Storage storage) throws ChatBotListException {
        String res = list.findItem(this.query);
        if (res == "") {
            ui.print("No results found.");
        } else {
            ui.print(String.format("Here are the matching tasks in your list:\n" + res));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof FindCommand)) {
            return false;
        } 
        //checked above
        @SuppressWarnings("unchecked")
        FindCommand c = (FindCommand) o;
        return c.query == this.query;
    }
}
