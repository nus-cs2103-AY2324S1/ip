package command;

import main.Main;
import main.Ui;

import java.util.HashMap;

public class Parser {

    private HashMap<String, ICommandHandler> commands;

    /**
     * Constructor for the Parser class
     */
    public Parser() {
        this.commands = new HashMap<String, ICommandHandler>();
    }

    /**
     * Register a new command
     *
     * @param header the command's header
     * @param handler The command's handler
     */
    public void registerCommand(String header, ICommandHandler handler) {
        this.commands.put(header, handler);
    }

    /**
     * Execute a command
     *
     * @param command the full command
     */
    public void executeCommand(String command) {
        assert(Ui.getInstance() != null);
        if(command.length() == 0) {
            Ui.getInstance().say("You didn't say anything.");
            return;
        }
        String[] splitedCommand = command.split(" ");
        try {
            if(command.equals("blah")) {
                throw new CommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            if(this.commands.containsKey(splitedCommand[0])) {
                this.commands.get(splitedCommand[0]).execute(command, splitedCommand);
            }
            else {
                this.commands.get("add").execute(command, splitedCommand);
            }
        }
        catch (CommandException e) {
            Ui.getInstance().sayError("An exception happened when executing this command: " + e.getMessage());
        }

    }

}
