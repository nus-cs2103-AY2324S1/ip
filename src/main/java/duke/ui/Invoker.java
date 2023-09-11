package duke.ui;

import java.util.HashMap;
import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.InvalidInputException;

public class Invoker {
    private HashMap<String, Command> commands;

    public Invoker() {
        this.commands = new HashMap<String, Command>();
    }
    
    /**
     * Adds a command to the invoker.
     * @param commandName Name of the command.
     * @param command Command object.
     */
    public void setCommand(String commandName, Command command) {
        this.commands.put(commandName, command);
    }
    
    /**
     * Executes a command.
     * @param input String input from user.
     * @throws DukeException If input is invalid.
     */
    public String execute(String input) throws DukeException {
        String commandName = input.split(" ")[0];
        if (this.commands.get(commandName) == null) {
            throw new InvalidInputException("Command not found");
        }

        return this.commands.get(commandName).execute(input);
    }
}
