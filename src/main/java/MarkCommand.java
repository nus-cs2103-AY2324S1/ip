public class MarkCommand extends Command {
    private int idx;
    private MarkCommand(){};

    public MarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(ChatBotList list, Ui ui, Storage storage) throws ChatBotListException{
        String res = list.markItem(this.idx);
        ui.print(String.format("Nice! I've marked this task as done:\n  " + res));
        storage.writeToSave(list);
    }
}
