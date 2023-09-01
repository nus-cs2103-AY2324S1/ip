package commands;

import records.ChatRecord;
import storage.SaveData;
import task.Task;

public class DeleteCommand extends Command {
    public static final String COMMAND_PHRASE = "delete";
    private int toDelete;
    public DeleteCommand(int i) {
        this.toDelete = i;
    }

    public void init(ChatRecord records) {
        this.chatRecord = records;
    }

    @Override
    public String execute() {
        Task task = this.chatRecord.deleteTask(toDelete);
        SaveData.saveData(this.chatRecord.toSave());
        return task.toString();
    }
}
