package roo.commands;

import roo.TaskList;
import roo.Ui;
import roo.RooException;
import roo.task.Todo;

public class TodoCommand extends Commands {
    private String input;

    public TodoCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(Ui ui, TaskList tasks) {
        try {
            String tsk = input.substring(4);
            return ui.add(new Todo(tsk));
        } catch (RooException exception) {
            return exception.getMessage();
        }
    }

}
