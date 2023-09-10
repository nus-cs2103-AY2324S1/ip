package roo.commands;

import roo.TaskList;
import roo.Ui;
import roo.RooException;
import roo.task.*;

public class DeadlineCommand extends Commands {
    private String input;

    public DeadlineCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(Ui ui, TaskList tasks) {
        try {
            if (!input.contains("/by")) {
                return "Please enter your task with this format: "
                        + "\"deadline task_description /by dd-MM-yyyy HH:mm (deadline)\"\n";
            }
            String tk = input.substring(8, input.indexOf("/") - 1);
            return ui.add(new Deadline(tk, input.substring(input.indexOf("/by") + 4)));
        } catch (RooException exception) {
            return exception.getMessage();
        }
    }

}
