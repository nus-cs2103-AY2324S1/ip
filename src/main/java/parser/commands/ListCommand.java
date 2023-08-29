package parser.commands;

import records.ChatRecord;

public class ListCommand extends Command {
    public ListCommand(ChatRecord records) {
        this.chatRecord = records;
    }

    @Override
    public String execute() {
        return chatRecord.listMessage();
    }
}
