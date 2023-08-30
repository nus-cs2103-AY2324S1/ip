package duke;
/**
 * Encapsulates the command to delete an item from the list.
 */
public class DeleteCommand extends Command {
    private int idx;

    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(ChatBotList list, Ui ui, Storage storage) throws ChatBotListException {
        String res = list.deleteItem(this.idx);
        ui.print(String.format("Noted. I've removed this task:\n  " + res));
        storage.writeToSave(list);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DeleteCommand)) {
            return false;
        }
        //checked above
        @SuppressWarnings("unchecked")
        DeleteCommand c = (DeleteCommand) o;
        return c.idx == this.idx;
    }
}
