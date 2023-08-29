package com.mimi.commands;

import com.mimi.main.ReadWriteData;
import com.mimi.main.Storage;
import com.mimi.main.Ui;

public class UnmarkCommand extends Command {
    boolean isCompleteCommand;
    int taskNumber;
    Storage storage;
    ReadWriteData readWriteData;
    public UnmarkCommand(boolean isCompleteCommand, int taskNumber, Storage storage, ReadWriteData readWriteData) {
        this.isCompleteCommand = isCompleteCommand;
        this.taskNumber = taskNumber;
        this.storage = storage;
        this.readWriteData = readWriteData;
    }
    @Override
    public void execute(){
        if (this.isCompleteCommand) {
            storage.unmark(taskNumber);
            readWriteData.updateFile();
        }
    }

    @Override
    public void uiResponse(Ui ui) {

        if (!this.isCompleteCommand) {
            ui.incompleteUnmarkCommand();
            return;
        }

        ui.separator();
    }


}
