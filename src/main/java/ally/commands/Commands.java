package ally.commands;

import ally.Storage;
import ally.Ui;
import ally.exceptions.AllyException;
import ally.tasks.AllyList;

/**
 * Abstract class for Commands.
 */
public abstract class Commands {
    public abstract String run(AllyList allyList, Ui ui, Storage storage) throws AllyException;
    public abstract boolean isExit();
}
