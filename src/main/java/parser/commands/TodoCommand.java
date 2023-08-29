package parser.commands;

import records.ChatRecord;
import task.Todo;
import task.Task;

public class TodoCommand extends Command {
    private String name;
    public TodoCommand(String name, ChatRecord records) {
        this.name = name;
        this.chatRecord = records;
    }

    @Override
    public String execute() {
        Todo td = new Todo(this.name);
        this.chatRecord.addTask(td);
        return td.toString();
    }
}
