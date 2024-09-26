package roo.commands;

import java.util.ArrayList;

import roo.TaskList;
import roo.Ui;
import roo.RooException;
import roo.task.Todo;

public class TodoCommand extends Command {
    private String input;
    private ArrayList<String> tags;

    public TodoCommand(String input, ArrayList<String> tags) {
        this.input = input;
        this.tags = tags;
    }

    @Override
    public String execute(Ui ui, TaskList tasks) {
        try {
            String tsk = input.substring(4);
            return ui.add(new Todo(tsk, this.tags));
        } catch (RooException exception) {
            return exception.getMessage();
        }
    }

}
