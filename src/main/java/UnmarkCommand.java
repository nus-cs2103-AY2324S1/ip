public class UnmarkCommand extends Command{
    private int idx;
    private UnmarkCommand(){};

    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(ChatBotList list, Ui ui) throws ChatBotListException{
        String res = list.unmarkItem(this.idx);
        ui.print(String.format("OK, I've marked this task as not done yet:\n  " + res));
    }
}
