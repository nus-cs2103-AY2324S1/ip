package duke;
/**
 * Encapsulates the command for displaying the list.
 */
public class DisplayListCommand extends Command {
    @Override
    public String execute(ChatBotList list, Storage storage) {
        storage.writeToSave(list);
        return list.toString();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof DisplayListCommand;
    }
}
