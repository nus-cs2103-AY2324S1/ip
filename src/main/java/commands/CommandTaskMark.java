package commands;

import client.Rock;
import io.Parser;
import storage.StorageException;
import tasks.TaskList;

/**
 * Representation of a command
 * to mark or unmark a task
 * as completed.
 * @author Alvis Ng (supermii2)
 */
public class CommandTaskMark extends Command {
    /** Represents whether this command marks or unmarks a task. */
    private boolean isMarking;
    /**
     * Constructor to create the
     * mark command
     * @param client Chatbot object
     */
    public CommandTaskMark(Rock client, boolean isMarking) {
        super(client);
        this.isMarking = isMarking;
    }
    /**
     * Marks or unmarks task from list
     * @param input Contains index of task to be
     * @throws IllegalArgumentException Thrown when invalid index is given.
     */
    @Override
    public String apply(Parser input) throws IllegalArgumentException, StorageException {
        String inputString = input.getDefaultString();
        try {
            int taskIdx = Integer.parseInt(inputString);
            client.markTask(taskIdx, this.isMarking);
            this.client.saveFile();
            String response = "";
            if (this.isMarking) {
                response += "Task marked successfully: \n";
            } else {
                response += "Task unmarked successfully: \n";
            }
            return(response);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid index given!");
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Illegal index given!");
        }
    }
}
