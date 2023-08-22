package java.doctor.Invoker;

import java.doctor.Command.Command;
import java.util.HashMap;

public class Invoker {
    private HashMap<String, Command> commands; 

    public Invoker() {
        this.commands = new HashMap<String, Command>();
    }

    public void setCommand(String commandName, Command command) {
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
