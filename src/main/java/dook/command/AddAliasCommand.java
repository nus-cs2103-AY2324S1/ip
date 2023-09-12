package dook.command;

import dook.DookException;
import dook.services.Parser;
import dook.services.Storage;
import dook.services.TaskList;

public class AddAliasCommand extends Command {
    private String alias;
    private CommandInfo cmd;
    public AddAliasCommand(String alias, CommandInfo cmd) {
        this.alias = alias;
        this.cmd = cmd;
    }
    @Override
    public String execute(Storage storage, TaskList taskList, Parser parser) throws DookException {
        parser.addMapping(alias, cmd);
        return String.format("Mapped %s to %s", alias, cmd.getName());
    }
}
