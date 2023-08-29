package parser.commands;

import records.ChatRecord;
import task.Task;

public class MarkCommand extends Command {
    private int toMark;
    public MarkCommand(int i, ChatRecord records) {
        this.toMark = i;
        this.chatRecord = records;
    }

    @Override
    public String execute() {
        Task task = chatRecord.setMark(toMark);
        return task.toString();
    }
}
