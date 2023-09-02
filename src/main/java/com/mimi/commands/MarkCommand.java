package com.mimi.commands;

import com.mimi.main.ReadWriteData;
import com.mimi.main.Storage;
import com.mimi.main.Ui;

/**
 * Representation of the Mark Command.
 * @author Yuheng
 */
public class MarkCommand extends Command {

    private boolean isCompleteCommand;
    private int taskNumber;

    private Storage storage;

    private ReadWriteData readWriteData;

    /**
     * Creates an instance of the mark Command.
     * @param isCompleteCommand true if the input has all the necessary components.
     * @param taskNumber the index of the task that is stored in Storage.
     * @param storage an instance of Storage.
     * @param readWriteData an instance of ReadWriteData.
     */
    public MarkCommand(boolean isCompleteCommand, int taskNumber, Storage storage, ReadWriteData readWriteData) {
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
            this.storage.mark(taskNumber);
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
            ui.incompleteMarkCommand();
            return;
        }

        ui.separator();
    }
}
