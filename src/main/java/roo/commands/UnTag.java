package roo.commands;

import roo.TaskList;
import roo.Ui;

public class UnTag extends Command {
    private String input;

    public UnTag(String input) {
        this.input = input;
    }

    @Override
    public String execute(Ui ui, TaskList tasks) {
        if (input.length() < 7) {
            return "Please untag your task using this format: "
                    + "\"untag [serial number]\"\n";
        }
        int t = Integer.parseInt(input.substring(6));
        if (t > tasks.size()) {
            return "We dunhave so many task lah =_=\n";
        }
        return ui.unTag(t - 1);
    }

}
