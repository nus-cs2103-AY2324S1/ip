package duke;
public class ExitCommand extends Command{
    @Override
    public void execute(ChatBotList list, Ui ui, Storage storage) throws ChatBotListException{
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ExitCommand;
    }
}
