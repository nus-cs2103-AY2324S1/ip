package rock.logic.commands;

import rock.client.Rock;
import rock.logic.io.Parser;
import rock.storage.exceptions.StorageException;

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
            // Displayed task list is 1-indexed but internal stored as 0-indexed
            int taskIdx = Integer.parseInt(inputString) - 1;
            client.getTaskList().mark(taskIdx, this.isMarking);
            this.client.saveFile();
            return isMarking ? "Got it! Task has been marked!\n" : "Got it! Task has been unmarked!\n";
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ERROR: Invalid number given!");
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("ERROR: No task exists at that number!");
        }
    }
}
