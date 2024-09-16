package ally.commands;

import ally.Storage;
import ally.Ui;
import ally.exceptions.AllyException;
import ally.tasks.AllyList;

public class HelpCommand extends Commands {
    @Override
    public String run(AllyList allyList, Ui ui, Storage storage) throws AllyException {
        return ui.showCommandExamples();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
