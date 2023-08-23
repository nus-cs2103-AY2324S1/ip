/**
 * Insert item into application state
 */
public class InsertCommand implements Command {
    private final DukeState state;
    public InsertCommand(DukeState state) {
        this.state = state;
    }

    /**
     * Add an item to the application state.
     *
     * @param input The user input of the item to add.
     */
    @Override
    public void run(String input) {
        state.insertItem(input);
        System.out.println(String.format("\t added: %s\n\t", input)
                + DukeConstants.HORIZONTAL_LINE);
    }
}
