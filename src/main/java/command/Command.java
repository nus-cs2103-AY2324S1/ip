package main.java.command;

import main.java.tasklist.TaskList;
import main.java.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui);
}
