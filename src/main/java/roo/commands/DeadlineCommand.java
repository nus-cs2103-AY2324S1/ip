package roo.commands;

import java.util.ArrayList;

import roo.RooException;
import roo.TaskList;
import roo.Ui;
import roo.task.Deadline;

/**
 * Command to create Deadline object.
 */
public class DeadlineCommand extends Command {
    private String input;
    private ArrayList<String> tags;

    public DeadlineCommand(String input, ArrayList<String> tags) {
        this.input = input;
        this.tags = tags;
    }

    @Override
    public String execute(Ui ui, TaskList tasks) {
        try {
            if (!input.contains("/by")) {
                return "Please enter your task with this format: "
                        + "\"deadline task_description /by dd-MM-yyyy HH:mm (deadline)\"\n";
            }
            String tk = input.substring(8, input.indexOf("/") - 1);
            return ui.add(new Deadline(tk, input.substring(input.indexOf("/by") + 4), this.tags));
        } catch (RooException exception) {
            return exception.getMessage();
        }
    }

}
