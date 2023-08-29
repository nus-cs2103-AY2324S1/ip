package commands;

import records.ChatRecord;
import task.Event;

import java.time.LocalDateTime;

public class EventCommand extends Command{

    private String name;
    private LocalDateTime startDate, endDate;

    public EventCommand(String name, LocalDateTime startDate, LocalDateTime endDate, ChatRecord records) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.chatRecord = records;
    }

    @Override
    public String execute() {
        Event ev = new Event(name, startDate, endDate);
        this.chatRecord.addTask(ev);
        return ev.toString();
    }
}
