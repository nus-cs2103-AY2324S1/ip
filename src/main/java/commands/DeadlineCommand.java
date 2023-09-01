package commands;

import records.ChatRecord;
import storage.SaveData;
import task.Deadline;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command{
    public static final String COMMAND_PHRASE = "deadline";

    private static final String COMMAND_DESC = "New Deadline Task added to list!";
    private String name;
    private LocalDateTime date;

    public DeadlineCommand(String name, LocalDateTime date) {
        this.name = name;
        this.date = date;
    }

    public void init(ChatRecord records) {
        this.chatRecord = records;
    }

    @Override
    public String execute() {
        Deadline ddl =  new Deadline(name, date);
        this.chatRecord.addTask(ddl);
        SaveData.saveData(this.chatRecord.toSave());
        return COMMAND_DESC + " " + ddl.toString();
    }

}
