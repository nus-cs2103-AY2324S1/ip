package Ally.Commands;

import Ally.AllyException;
import Ally.AllyList;
import Ally.Storage;
import Ally.Ui;

public class ExitCommand extends Commands {

    @Override
    public void run(AllyList allyList, Ui ui, Storage storage) {
        try {
            ui.bye();
        } catch (AllyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
