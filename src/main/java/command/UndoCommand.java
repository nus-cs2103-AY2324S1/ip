package main.java.command;

import main.java.tasklist.TaskList;
import main.java.ui.Ui;

public class UndoCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui) {
        taskList.revertPreviousTaskList();
        return "Task has been undone!";
    }
}
