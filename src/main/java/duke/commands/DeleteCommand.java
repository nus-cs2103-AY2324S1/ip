package duke.commands;

import duke.records.ChatRecord;
import duke.storage.SaveData;
import duke.task.InvalidTaskException;
import duke.task.Task;

/**
 * The command to delete a Task.
 * @author Toh Li Yuan (A0255811H)
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_PHRASE = "delete";
    private static final String COMMAND_DESC = "The following task has been deleted: ";
    private int toDelete;
    public DeleteCommand(int i) {
        this.toDelete = i;
    }

    public void init(ChatRecord records) {
        this.chatRecord = records;
    }

    /**
     * Executes the created Delete Command.
     * The selected Task will be deleted from the record.
     *
     * @return The string representation of the Task to be deleted.
     */
    @Override
    public String execute() {
        assert toDelete < this.chatRecord.getCount();
        try {
            Task task = this.chatRecord.deleteTask(toDelete);
            SaveData.saveData(this.chatRecord.toSave());
            return COMMAND_DESC + " " + task.toString();
        } catch (InvalidTaskException e) {
            return e.getMessage();
        }
    }

    @Override
    public String toString() {
        return COMMAND_PHRASE + " " + toDelete;
    }
}
