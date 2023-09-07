package duke;
public class UnmarkCommand extends Command {
    private int idx;

    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public String execute(ChatBotList list, Storage storage) throws ChatBotListException {
        String res = list.unmarkItem(this.idx);
        storage.writeToSave(list);
        return String.format("OK, I've marked this task as not done yet:\n  " + res);
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
