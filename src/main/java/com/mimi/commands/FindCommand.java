package com.mimi.commands;

import com.mimi.main.Storage;
import com.mimi.main.Ui;

/**
 * Representation of the find Command.
 * @author Yuheng
 */
public class FindCommand extends Command {
    private String searchTerm;
    private Storage storage;

    /**
     * Creates an instance of the find command without any search terms.
     */
    public FindCommand() {
    }

    /**
     * Creates an instance of the find Command.
     * @param searchTerm the string that is used to find matching descriptions in Storage.
     * @param storage an instance of Storage.
     */
    public FindCommand(String searchTerm, Storage storage) {
        this.searchTerm = searchTerm;
        this.storage = storage;
    }
    @Override
    public void execute() {
        if (searchTerm != null && !searchTerm.equals("")) {
            this.storage.search(searchTerm);
        }

    }

    @Override
    public void uiResponse(Ui ui) {
        if (this.searchTerm == null || this.searchTerm.equals("")) {
            ui.incompleteSearchCommand();
        }
        ui.separator();
    }
}
