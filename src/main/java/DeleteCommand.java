/**
 * Encapsulates the command to delete an item from the list.
 */
public class DeleteCommand extends Command{
    private int idx;
    private DeleteCommand(){};

    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(ChatBotList list, Ui ui) throws ChatBotListException{
        String res = list.deleteItem(this.idx);
        ui.print(String.format("Noted. I've removed this task:\n  " + res));
    }
}
