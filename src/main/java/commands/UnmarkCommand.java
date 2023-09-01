package commands;

import records.ChatRecord;
import storage.SaveData;
import task.Task;

public class UnmarkCommand extends Command {
    public static final String COMMAND_PHRASE = "unmark";
    private int toUnmark;

    public UnmarkCommand(int i) {
        this.toUnmark = i;
    }

    public void init(ChatRecord records) {
        this.chatRecord = records;
    }

    @Override
    public String execute() {
        Task task = chatRecord.setUnmark(toUnmark);
        SaveData.saveData(this.chatRecord.toSave());
        return task.toString();
    }
}
