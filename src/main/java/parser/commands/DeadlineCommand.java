package parser.commands;

import records.ChatRecord;
import task.Deadline;
import task.Task;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command{

    private String name;
    private LocalDateTime date;

    public DeadlineCommand(String name, LocalDateTime date, ChatRecord records) {
        this.name = name;
        this.date = date;
        this.chatRecord = records;
    }

    @Override
    public String execute() {
        Deadline ddl =  new Deadline(name, date);
        this.chatRecord.addTask(ddl);
        return ddl.toString();
    }

}
