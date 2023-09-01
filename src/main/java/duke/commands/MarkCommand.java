package duke.commands;

import duke.records.ChatRecord;
import duke.storage.SaveData;
import duke.task.Task;

/**
 * The command to mark a task as complete.
 * @author Toh Li Yuan (A0255811H)
 */
public class MarkCommand extends Command {
    public static final String COMMAND_PHRASE = "mark";
    private static final String COMMAND_DESC = "Marked the following task as completed!";
    private int toMark;

    /**
     * Creates a command to mark a task as done.
     *
     * @param i the task number of the task to be marked in the list.
     */
    public MarkCommand(int i) {
        this.toMark = i;
    }

    public void init(ChatRecord records) {
        this.chatRecord = records;
    }

    /**
     * Executes the created Mark Command.
     *
     * @return The string representation of task to be marked.
     */
    @Override
    public String execute() {
        Task task = chatRecord.setMark(toMark);
        SaveData.saveData(this.chatRecord.toSave());
        return COMMAND_DESC + "\n" + task.toString();
    }

    @Override
    public String toString() {
        return COMMAND_PHRASE + " " + toMark;
    }
}
