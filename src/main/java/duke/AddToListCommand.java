package duke;
/**
 * Encapsulates the command to add items to the list.
 */
public class AddToListCommand extends Command {
    private DukeEnvironmentConstants.taskType type;
    private String[] queries;

    public AddToListCommand(DukeEnvironmentConstants.taskType type, String[] queries) {
        this.type = type;
        this.queries = queries;
    }

    @Override
    public void execute(ChatBotList list, Ui ui, Storage storage) throws IllegalChatBotListArgumentException{
        String res = list.addToList(queries, type);
        ui.print("Got it. I've added this task:\n" + res 
                + "\nNow you have " + list.getLength() + " tasks in the list.");
        storage.writeToSave(list);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AddToListCommand)) {
            return false;
        } 
        //checked above
        @SuppressWarnings("unchecked")
        AddToListCommand c = (AddToListCommand) o;
        return c.queries.equals(this.queries) && c.type.equals(this.type);
    }
}
