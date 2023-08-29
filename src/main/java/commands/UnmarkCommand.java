package commands;

import records.ChatRecord;
import task.Task;

public class UnmarkCommand extends Command {
    private int toUnmark;

    public UnmarkCommand(int i, ChatRecord records) {
        this.toUnmark = i;
        this.chatRecord = records;
    }

    @Override
    public String execute() {
        Task task = chatRecord.setUnmark(toUnmark);
        return task.toString();
    }
}
