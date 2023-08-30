package duke;

/**
 * Encapsulates the command for marking a task as completed.
 */
public class MarkCommand extends Command {
    private int idx;

    public MarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(ChatBotList list, Ui ui, Storage storage) throws ChatBotListException {
        String res = list.markItem(this.idx);
        ui.print(String.format("Nice! I've marked this task as done:\n  " + res));
        storage.writeToSave(list);
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MarkCommand)) {
            return false;
        }
        //checked above
        @SuppressWarnings("unchecked")
        MarkCommand c = (MarkCommand) o;
        return c.idx == this.idx;
    }
}
