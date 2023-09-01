package duke.commands;

import duke.records.ChatRecord;
import duke.storage.SaveData;
import duke.task.Task;

public class UnmarkCommand extends Command {
    public static final String COMMAND_PHRASE = "unmark";
    private static final String COMMAND_DESC = "Marked the following duke.task as incomplete!";
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
        return COMMAND_DESC + "\n" + task.toString();
    }
}
