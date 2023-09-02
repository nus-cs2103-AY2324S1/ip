package com.mimi.commands;

import com.mimi.main.Storage;
import com.mimi.main.Ui;

/**
 * Representation of the List Command.
 * @author Yuheng
 */
public class ListCommand extends Command {
    private Storage storage;

    /**
     * Creates an instance of the List Command.
     * @param storage an instance of Storage.
     */
    public ListCommand(Storage storage) {
        this.storage = storage;
    }

    /**
     * Executes the given command.
     */
    @Override
    public void execute() {
        this.storage.listItems();
    }

    /**
     * Calls the ui to give the appropriate response based on the type of command.
     * @param ui Ui class instance
     */
    @Override
    public void uiResponse(Ui ui) {
        ui.separator();
    }
}
