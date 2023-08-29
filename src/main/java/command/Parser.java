package command;

import main.Main;

import java.util.HashMap;

public class Parser {

    private HashMap<String, ICommandHandler> commands;

    public Parser(){
        this.commands = new HashMap<String, ICommandHandler>();
    }

    public void registerCommand(String command, ICommandHandler handler){
        this.commands.put(command, handler);
    }

    public void executeCommand(String command){
        if(command.length() == 0){
            Main.getInstance().getUi().say("You didn't say anything.");
            return;
        }
        String[] splitedCommand = command.split(" ");
        try{
            if(command.equals("blah")){
                throw new CommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            if(this.commands.containsKey(splitedCommand[0])){
                this.commands.get(splitedCommand[0]).execute(command, splitedCommand);
            }
            else{
                this.commands.get("add").execute(command, splitedCommand);
            }
        }
        catch (CommandException e){
            Main.getInstance().getUi().say("An exception happened when executing this command: " + e.getMessage());
        }

    }

}
