package Ally.Commands;

import Ally.AllyException;
import Ally.AllyList;
import Ally.Storage;
import Ally.Ui;
public abstract class Commands {
    public abstract void run (AllyList allyList, Ui ui, Storage storage) throws AllyException;
    public abstract boolean isExit();
}
