package com.mimi.commands;

import com.mimi.main.ReadWriteData;
import com.mimi.main.Storage;
import com.mimi.main.Ui;

/**
 * Representation of the Delete Command.
 * @author Yuheng
 */
public class DeleteCommand extends Command {
    private boolean isCompleteCommand;
    private int taskNumber;
    private Storage storage;
    private ReadWriteData readWriteData;

    /**
     * Creates an instance of the delete command.
     * @param isCompleteCommand true if the input has all the necessary components.
     * @param taskNumber the position that the task is stored in the Storage.
     * @param storage an instance of Storage that stores the previous tasks.
     * @param readWriteData an instance of ReadWriteData that writes and accesses items in the hard disk.
     */
    public DeleteCommand(boolean isCompleteCommand, int taskNumber, Storage storage, ReadWriteData readWriteData) {
        this.isCompleteCommand = isCompleteCommand;
        this.taskNumber = taskNumber;
        this.storage = storage;
        this.readWriteData = readWriteData;
    }
    /**
     * Executes the given command.
     */
    @Override
    public void execute() {
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
