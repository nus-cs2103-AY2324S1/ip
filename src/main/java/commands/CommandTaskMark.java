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
    public void accept(Parser input) throws IllegalArgumentException, StorageException {
        String inputString = input.getDefaultString();
        TaskList taskList = this.client.getTaskList();
        try {
            int taskIdx = Integer.parseInt(inputString);
            if (taskIdx < 1 || taskIdx > taskList.size()) {
                throw new IllegalArgumentException("Invalid index given!");
            } else if (taskList.getTask(taskIdx - 1).isCompleted()) {
                if (this.isMarking) {
                    throw new IllegalArgumentException("Task already marked!");
                } else {
                    throw new IllegalArgumentException("Task already unmarked!");
                }

            } else {
                taskList.getTask(taskIdx - 1).setCompleted(this.isMarking);
                this.client.getStorage().saveSaveFile();
                String response = "";
                if (this.isMarking) {
                    response += "Task marked successfully: \n";
                } else {
                    response += "Task unmarked successfully: \n";
                }
                response += taskList.getTask(taskIdx - 1).toString();
                this.client.getUi().respond(response);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid index given!");
        }
    }
}
