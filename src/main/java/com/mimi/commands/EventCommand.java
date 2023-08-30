package com.mimi.commands;

import com.mimi.main.ReadWriteData;
import com.mimi.main.Storage;
import com.mimi.main.Ui;
import com.mimi.tasks.Event;

import java.time.LocalDateTime;

public class EventCommand extends Command {
    boolean isCompleteCommand;
    String eventName;
    LocalDateTime startTime;
    LocalDateTime endTime;
    Storage storage;
    ReadWriteData readWriteData;

    public EventCommand(boolean isCompleteCommand, String eventName, LocalDateTime startTime,
                        LocalDateTime endTime, Storage storage, ReadWriteData readWriteData) {
        this.isCompleteCommand = isCompleteCommand;
        this.eventName = eventName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.storage = storage;
        this.readWriteData = readWriteData;

    }

    /**
     * Executes the given command.
     */
    @Override
    public void execute(){
        if (!this.isCompleteCommand || this.startTime == LocalDateTime.MIN || this.endTime == LocalDateTime.MIN) {
            return;
        }

        Event event = new Event(this.eventName, this.startTime, this.endTime);
        this.storage.add(event);
        this.readWriteData.write(event);
    }

    /**
     * Calls the ui to give the appropriate response based on the type of command.
     * @param ui Ui class instance
     */
    @Override
    public void uiResponse(Ui ui) {
        if (!this.isCompleteCommand) {
            ui.incompleteEventCommand();
        } else if (this.startTime == LocalDateTime.MIN || this.endTime == LocalDateTime.MIN) {
            ui.wrongTimeFormat();
        }

        ui.separator();
    }
}
