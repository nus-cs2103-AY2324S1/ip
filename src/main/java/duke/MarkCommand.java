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
    public String execute(ChatBotList list, Storage storage) throws ChatBotListException {
        String res = list.markItem(this.idx);
        storage.writeToSave(list);
        return String.format("Nice! I've marked this task as done:\n  " + res);
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
