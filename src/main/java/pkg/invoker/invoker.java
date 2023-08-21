package pkg.command;

import java.util.HashMap;

public class invoker {
    private HashMap<String, command> commands; 

    public invoker() {
        this.commands = new HashMap<String, command>();
    }

    public void setCommand(String commandName, command command) {
        this.commands.put(commandName, command);
    }

    public void execute(String input) {
        String commandName = input.split(" ")[0];
        if (this.commands.get(commandName) == null) {
            System.out.println("Command not found");
            return;
        }

        this.commands.get(commandName).execute(input);
    }
}
