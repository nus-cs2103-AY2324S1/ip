package com.mimi.commands;

import com.mimi.main.Storage;
import com.mimi.ui.Ui;

/**
 * Representation of the Remind Command.
 * @author Yuheng
 */
public class RemindCommand extends Command {
    private Storage storage;

    /**
     * Creates a new instance of the Remind Command.
     * @param storage an instance of Storage.
     */
    public RemindCommand(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void execute() {
        storage.remind();
    }

    @Override
    public void uiResponse(Ui ui) { }
}
