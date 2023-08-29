package com.mimi.commands;
import com.mimi.main.Ui;

public abstract class Command {

    public abstract void execute();

    public abstract void uiResponse(Ui ui);

    public boolean isExit() {
        return false;
    }
}
