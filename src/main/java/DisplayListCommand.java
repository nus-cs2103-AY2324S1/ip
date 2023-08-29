/**
 * Encapsulates the command for displaying the list
 */
public class DisplayListCommand extends Command{
    @Override
    public void execute(ChatBotList list, Ui ui, Storage storage) {
        ui.print(list.toString());
        storage.writeToSave(list);
    }
}
