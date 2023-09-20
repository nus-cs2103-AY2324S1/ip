package com.mimi.commands;

import java.time.LocalDateTime;

import com.mimi.main.ReadWriteData;
import com.mimi.main.Storage;
import com.mimi.tasks.Event;
import com.mimi.ui.Ui;

/**
 * Representation of the Event Command.
 * @author Yuheng
 */
public class EventCommand extends Command {
    private boolean isCompleteCommand;
    private String eventName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Storage storage;
    private ReadWriteData readWriteData;

    /**
     * Creates an instance of the event Command.
     * @param isCompleteCommand true if the input given had all the necessary fields.
     * @param eventName The string representation of the name of the event.
     * @param startTime The start time of the event
     * @param endTime The end time of the event
     * @param storage A Storage instance that stores the previous tasks.
     * @param readWriteData A ReadWriteData instance that helps access and write data to the hard disk.
     */
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
    public void execute() {
        if (!this.isCompleteCommand || this.startTime == LocalDateTime.MIN
                || this.endTime == LocalDateTime.MIN || this.isOverdue()
                || this.eventName.isBlank() || !this.isValid()) {
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
        } else if (this.isOverdue()) {
            ui.displayOverdue();
        } else if (this.eventName.isBlank()) {
            ui.incompleteInformation();
        } else if (!this.isValid()) {
            ui.invalidEventTime();
        }
    }

    private boolean isOverdue() {
        LocalDateTime currentTime = LocalDateTime.now();

        return this.startTime.isBefore(currentTime);
    }

    private boolean isValid() {
        return this.startTime.isBefore(this.endTime);
    }
}
