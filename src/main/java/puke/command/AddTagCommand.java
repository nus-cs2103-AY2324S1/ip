package puke.command;

import puke.managers.DataHandler;
import puke.managers.PukeException;
import puke.managers.TaskList;

/**
 * Command class to add a tag to a task
 */
public class AddTagCommand extends Command {
    private static final String TAG_MESSAGE = "Very well. I shall now proceed to allocate the specified word, "
            + "colloquially known as a tag, to the activity that you had previously indicated to be of note, previously"
            + "stated to be known as a task, for the purpose of some sort of indication or marking.";
    private final int index;
    private final String tag;

    /**
     * Creates a command to add a tag
     * @param rest the rest of the line
     */
    public AddTagCommand(String rest) throws PukeException {
        super(false, rest.isEmpty());
        if (rest.isEmpty()) {
            throw new PukeException();
        }
        String[] inputs = rest.split(" ", 2);
        this.index = Integer.parseInt(inputs[0]);
        this.tag = inputs[1];
    }

    /**
     * Executes the command by returning the appropriate message for adding a tag
     * @param tl the TaskList
     * @return the message string
     */
    public String execute(TaskList tl) {
        try {
            tl.addTag(this.index, tag);
            DataHandler.writeToDatabase(tl);
            return generateMessage();
        } catch (Exception PukeException) {
            return ERROR_MESSAGE;
        }
    }
    @Override
    public boolean equals(Object other) {
        boolean isAddTagCommand = other instanceof AddTagCommand;
        boolean isSameCommand = other.toString().equals(toString());
        return isAddTagCommand && isSameCommand;
    }
    @Override
    public String toString() {
        return "addTag " + this.index;
    }
    private String generateMessage() {
        return TAG_MESSAGE;
    }
}
