package com.mimi.commands;

import com.mimi.main.Storage;
import com.mimi.main.Ui;

public class ListCommand extends Command {
    Storage storage;

    public ListCommand(Storage storage) {
        this.storage = storage;
    }
    @Override
    public void execute(){
        this.storage.listItems();
    }

    @Override
    public void uiResponse(Ui ui) {
        ui.separator();
    }
}
