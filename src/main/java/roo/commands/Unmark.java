package roo.commands;

import roo.TaskList;
import roo.Ui;

public class Unmark extends Command {
    private String input;

    public Unmark(String input) {
        this.input = input;
    }

    @Override
    public String execute(Ui ui, TaskList tasks) {
        if (input.length() < 8) {
            return "Please unmark your task using this format: "
                    + "\"unmark [serial number]\"\n";
        }
        int t = Integer.parseInt(input.substring(7));
        if (t > tasks.size()) {
            return "We dunhave so many task lah =_=\n";
        } else if (!tasks.isDone(t - 1)) {
            return "Weihh... It's unmark ehhh\n";
        }
        return ui.markUndone(t - 1);
    }

}
