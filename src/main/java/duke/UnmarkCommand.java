package duke;

/**
 * Encapsulates the command for marking a task as completed.
 */
public class UnmarkCommand extends Command{
    private int idx;

    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(ChatBotList list, Ui ui, Storage storage) throws ChatBotListException{
        String res = list.unmarkItem(this.idx);
        ui.print(String.format("OK, I've marked this task as not done yet:\n  " + res));
        storage.writeToSave(list);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UnmarkCommand)) {
            return false;
        } 
        //checked above
        @SuppressWarnings("unchecked")
        UnmarkCommand c = (UnmarkCommand) o;
        return c.idx == this.idx;
    }
}
