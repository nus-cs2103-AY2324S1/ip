package roo.commands;

import roo.TaskList;
import roo.Ui;

public class Delete extends Commands {
    private String input;

    public Delete(String input) {
        this.input = input;
    }

    @Override
    public String execute(Ui ui, TaskList tasks) {
        if (input.length() < 8) {
            return "Please delete your task using this format: "
                    + "\"delete [serial number]\"\n";
        }
        int v = Integer.parseInt(input.substring(7));
        if (v > tasks.size()) {
            return "We dunhave so many task lah =_=\n";
        }
        return ui.delete(v - 1);
    }

}
