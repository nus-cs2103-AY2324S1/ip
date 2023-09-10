package roo.commands;

import roo.TaskList;
import roo.Ui;

public class Mark extends Commands {
    private String input;

    public Mark(String input) {
        this.input = input;
    }

    @Override
    public String execute(Ui ui, TaskList tasks) {
        if (input.length() < 6) {
            return "Please mark your task using this format: "
                    + "\"mark [serial number]\"\n";
        }
        int u = Integer.parseInt(input.substring(5));
        if (u > tasks.size()) {
            return "We dunhave so many task lah =_=\n";
        } else if (tasks.isDone(u - 1)) {
            return "Weihh... It's already mark ehhh\n";
        }
        return ui.markDone(u - 1);
    }

}
