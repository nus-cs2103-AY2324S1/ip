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
    public String execute(ChatBotList list, Storage storage) throws ChatBotListException {
        String res = list.deleteItem(this.idx);
        storage.writeToSave(list);
        return String.format("Noted. I've removed this task:\n  " + res);
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
