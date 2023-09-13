package roo.commands;

import roo.TaskList;
import roo.Ui;

import java.util.ArrayList;

public class Tag extends Command{
    private String input;
    private ArrayList<String> tags;

    public Tag(String input, ArrayList<String> tags) {
        this.input = input;
        this.tags = tags;
    }

    @Override
    public String execute(Ui ui, TaskList tasks) {
        if (input.length() < 4) {
            return "What do you want to tag ahh??\n";
        }
        int u = Integer.parseInt(input.substring(4).replace(" ", ""));
        if (u > tasks.size()) {
            return "We dunhave so many task lah =_=\n";
        }
        return ui.tag(u - 1, this.tags);
    }

}
