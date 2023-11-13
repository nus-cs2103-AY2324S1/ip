package duke;

/**
 * Encapsulates the command for snoozing a Deadline.
 */
public class SnoozeCommand extends Command {
    private int idx;

    public SnoozeCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public String execute(ChatBotList list, Storage storage) throws ChatBotListException {
        //
        String res = list.snoozeItem(this.idx);
        storage.writeToSave(list);
        return String.format("Ok, I have snoozed this task by 1 day:\n  " + res);
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof SnoozeCommand)) {
            return false;
        }
        assert !(o instanceof SnoozeCommand) : this.getClass() + ".equals: short circuit failed";
        //checked above
        @SuppressWarnings("unchecked")
        SnoozeCommand c = (SnoozeCommand) o;
        return c.idx == this.idx;
    }
}
