package parser.commands;

import records.ChatRecord;
import task.Task;

public class DeleteCommand extends Command {
    private int toDelete;
    public DeleteCommand(int i, ChatRecord records) {
        this.chatRecord = records;
        this.toDelete = i;
    }

    @Override
    public String execute() {
        Task task = this.chatRecord.deleteTask(toDelete);
        return task.toString();
    }
}
