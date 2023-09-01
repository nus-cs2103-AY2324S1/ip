package duke.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.records.ChatRecord;
import duke.storage.SaveData;
import duke.task.Deadline;

public class DeadlineCommand extends Command {
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

    /**
     * Executes the created Deadline Command.
     * A new Deadline Task will be added to the records.
     *
     * @return The string to be displayed as feedback to the user.
     */
    @Override
    public String execute() {
        Deadline ddl = new Deadline(name, date);
        this.chatRecord.addTask(ddl);
        SaveData.saveData(this.chatRecord.toSave());
        return COMMAND_DESC + " " + ddl.toString();
    }

    @Override
    public String toString() {
        return COMMAND_PHRASE + " " + name + " /by " + date.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }

}
