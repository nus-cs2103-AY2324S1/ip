package duke.commands;

import duke.records.ChatRecord;
import duke.storage.SaveData;
import duke.task.Task;

/**
 * The command to unmark a task.
 * @author Toh Li Yuan (A0255811H)
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_PHRASE = "unmark";
    private static final String COMMAND_DESC = "Marked the following task as incomplete!";
    private int toUnmark;

    /**
     * Creates a command to unmark a Task as done.
     *
     * @param i the task number of the task to be unmarked in the list.
     */
    public UnmarkCommand(int i) {
        this.toUnmark = i;
    }

    public void init(ChatRecord records) {
        this.chatRecord = records;
    }

    /**
     * Executes the created Unmark Command.
     *
     * @return The string representation of task to be unmarked.
     */
    @Override
    public String execute() {
        Task task = chatRecord.setUnmark(toUnmark);
        SaveData.saveData(this.chatRecord.toSave());
        return COMMAND_DESC + "\n" + task.toString();
    }

    @Override
    public String toString() {
        return COMMAND_PHRASE + " " + toUnmark;
    }
}
