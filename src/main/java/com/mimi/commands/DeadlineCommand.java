package com.mimi.commands;

import com.mimi.main.ReadWriteData;
import com.mimi.main.Storage;
import com.mimi.main.Ui;
import com.mimi.tasks.Deadline;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command {

    boolean isCompleteCommand;
    String taskName;
    Storage storage;
    ReadWriteData readWriteData;
    LocalDateTime deadlineTime;

public DeadlineCommand(boolean isCompleteCommand, String taskName,
                       LocalDateTime deadlineTime, Storage storage, ReadWriteData readWriteData) {
        this.isCompleteCommand = isCompleteCommand;
        this.taskName = taskName;
        this.storage = storage;
        this.readWriteData = readWriteData;
        this.deadlineTime = deadlineTime;
    }
    @Override
    public void execute() {
        //first check if the command is complete and if the time format is correct.
        if (!this.isCompleteCommand || this.deadlineTime == LocalDateTime.MIN) {
            return;
        }

        Deadline deadline = new Deadline(this.taskName, this.deadlineTime);
        this.storage.add(deadline);
        this.readWriteData.write(deadline);
    }

    @Override
    public void uiResponse(Ui ui) {
        if (!this.isCompleteCommand) {
            ui.incompleteDeadlineCommand();
        } else if (this.deadlineTime == LocalDateTime.MIN) {
            ui.wrongTimeFormat();
        }

        ui.separator();
    }

}
