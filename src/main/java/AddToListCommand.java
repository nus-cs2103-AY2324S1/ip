/**
 * Encapsulates the command to add items to the list.
 */
public class AddToListCommand extends Command {
    private DukeEnvironmentConstants.taskType type;
    private String[] queries;
    private AddToListCommand(){};

    public AddToListCommand(DukeEnvironmentConstants.taskType type, String[] queries) {
        this.type = type;
        this.queries = queries;
    }

    @Override
    public void execute(ChatBotList list, Ui ui) throws IllegalChatBotListArgumentException{
        String res = list.addToList(queries, type);
        System.out.println("Got it. I've added this task:\n" + res);
                            System.out.println("Now you have " + list.getLength() + " tasks in the list.");
    }
}
