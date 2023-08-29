package com.mimi.commands;

import com.mimi.main.ReadWriteData;
import com.mimi.main.Storage;
import com.mimi.main.Ui;

public class MarkCommand extends Command {

    private boolean isCompleteCommand;
    private int taskNumber;

    Storage storage;

    ReadWriteData readWriteData;

    public MarkCommand(boolean isCompleteCommand, int taskNumber, Storage storage, ReadWriteData readWriteData) {
        this.isCompleteCommand = isCompleteCommand;
        this.taskNumber = taskNumber;
        this.storage = storage;
        this.readWriteData = readWriteData;
    }

    @Override
    public void execute(){
        if (this.isCompleteCommand) {
            this.storage.mark(taskNumber);
            readWriteData.updateFile();
        }
    }



    @Override
    public void uiResponse(Ui ui) {
        if (!this.isCompleteCommand) {
            ui.incompleteMarkCommand();
            return;
        }

        ui.separator();
    }
}
