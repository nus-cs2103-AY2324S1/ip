package duke.list;

import duke.command.Command;

import java.util.ArrayList;
import java.util.HashMap;

public class CommandList {
    private HashMap<String, String> commandNameList;
    private HashMap<String, ArrayList<String>> commandTypeList;

    public CommandList() {

    }

    public void addCommandName(String commandType, String commandName) {
        commandNameList.put(commandName, commandType);
        ArrayList<String> nameList = commandTypeList.get(commandType);
        nameList.add(commandName);
        commandTypeList.put(commandType, nameList);
    }

    public void deleteCommandName(String commandName) {
        commandNameList.remove(commandName);
        String commandType = commandNameList.get(commandName);
        ArrayList<String> nameList = commandTypeList.get(commandType);
        nameList.remove(commandName);
        commandTypeList.put(commandType, nameList);
    }

    public String getCommandType(String commandName) {
        return commandNameList.get(commandName);
    }

    public String listAllCommandName() {
        String output = "";
        for (String type: commandTypeList.keySet()) {
            output = output + type + ": " + commandTypeList.get(type).toString() + "\n";
        }
        return output;
    }


}
