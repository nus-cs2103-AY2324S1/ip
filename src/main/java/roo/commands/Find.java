package roo.commands;

import roo.TaskList;
import roo.Ui;

public class Find extends Command {
    private String input;

    public Find(String input) {
        this.input = input;
    }

    @Override
    public String execute(Ui ui, TaskList tasks) {
        if (input.length() < 6) {
            return "What do you want to find ahh??\n";
        }
        String keyword = input.substring(6);
        return ui.find(keyword);
    }

}
