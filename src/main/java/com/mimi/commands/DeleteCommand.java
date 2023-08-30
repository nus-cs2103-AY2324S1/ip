package com.mimi.commands;

import com.mimi.main.ReadWriteData;
import com.mimi.main.Storage;
import com.mimi.main.Ui;

public class DeleteCommand extends Command {
    boolean isCompleteCommand;
    int taskNumber;
    Storage storage;
    ReadWriteData readWriteData;
    public DeleteCommand (boolean isCompleteCommand, int taskNumber, Storage storage, ReadWriteData readWriteData) {
        this.isCompleteCommand = isCompleteCommand;
        this.taskNumber = taskNumber;
        this.storage = storage;
        this.readWriteData = readWriteData;
    }
    /**
     * Executes the given command.
     */
    @Override
    public void execute(){
        if (this.isCompleteCommand) {
            this.storage.delete(taskNumber);
            readWriteData.updateFile();
        }
    }

    /**
     * Calls the ui to give the appropriate response based on the type of command.
     * @param ui Ui class instance
     */
    @Override
    public void uiResponse(Ui ui) {
        if (!this.isCompleteCommand) {
            ui.incompleteDeleteCommand();
            return;
        }
        ui.separator();
    }
}
