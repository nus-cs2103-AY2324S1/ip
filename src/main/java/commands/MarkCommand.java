package commands;

import records.ChatRecord;
import storage.SaveData;
import task.Task;

public class MarkCommand extends Command {
    public static final String COMMAND_PHRASE = "mark";
    private int toMark;
    public MarkCommand(int i) {
        this.toMark = i;
    }

    public void init(ChatRecord records) {
        this.chatRecord = records;
    }

    @Override
    public String execute() {
        Task task = chatRecord.setMark(toMark);
        SaveData.saveData(this.chatRecord.toSave());
        return task.toString();
    }
}
