package duke;
/**
 * Encapsulates the command for displaying the list.
 */
public class DisplayListCommand extends Command{
    @Override
    public void execute(ChatBotList list, Ui ui, Storage storage) {
        ui.print(list.toString());
        storage.writeToSave(list);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof DisplayListCommand;
    }
}
