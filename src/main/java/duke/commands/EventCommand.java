package duke.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.records.ChatRecord;
import duke.storage.SaveData;
import duke.task.Event;

/**
 * The command to add an Event task.
 * @author Toh Li Yuan (A0255811H)
 */
public class EventCommand extends Command {
    public static final String COMMAND_PHRASE = "event";
    private static final String COMMAND_DESC = "New Event Task added to list!";
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    /**
     * Creates a command to add an Event.
     *
     * @param name the name of the Event.
     * @param startDate the starting date of the Event.
     * @param endDate the ending date of the Event.
     */
    public EventCommand(String name, LocalDateTime startDate, LocalDateTime endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void init(ChatRecord records) {
        this.chatRecord = records;
    }

    /**
     * Executes the created Event Command.
     * A new Event Task will be added to the records.
     *
     * @return The string to be displayed as feedback to the user.
     */
    @Override
    public String execute() {
        Event ev = new Event(name, startDate, endDate);
        this.chatRecord.addTask(ev);
        SaveData.saveData(this.chatRecord.toSave());
        return COMMAND_DESC + " " + ev.toString();
    }

    @Override
    public String toString() {
        return COMMAND_PHRASE + " " + name + " /from "
                + startDate.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"))
                + " /to " + endDate.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }
}
