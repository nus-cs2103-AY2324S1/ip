package duke.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Command list class that stores command names and types.
 */
public class CommandList {
    private HashMap<String, String> commandNameList;
    private HashMap<String, ArrayList<String>> commandTypeList;

    public CommandList() {
        commandNameList = new HashMap<>();
        commandTypeList = new HashMap<>();
        addCommandName("todo", "todo");
        addCommandName("event", "event");
        addCommandName("deadline", "deadline");
        addCommandName("delete", "delete");
        addCommandName("bye", "bye");
        addCommandName("find", "find");
        addCommandName("list", "list");
        addCommandName("mark", "mark");
        addCommandName("unmark", "unmark");
        addCommandName("set", "set");
        addCommandName("unset", "unset");
        addCommandName("load", "load");
        addCommandName("change", "change");
        addCommandName("help", "help");
        addCommandName("display", "display");
    }

    public void addCommandName(String commandType, String commandName) {
        if (commandNameList.containsKey(commandName)) {
            return;
        }
        commandNameList.put(commandName, commandType);
        ArrayList<String> nameList;
        if (commandTypeList.containsKey(commandType)) {
            nameList = commandTypeList.get(commandType);
        } else {
            nameList = new ArrayList<>();
        }
        nameList.add(commandName);
        commandTypeList.put(commandType, nameList);
    }

    public void deleteCommandName(String commandType, String commandName) {
        commandNameList.remove(commandName);
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

    public void saveCommand(String[] array) {
        String commandType = array[0];
        int size = array.length;

        for (int i = 1; i < size; i++) {
            addCommandName(commandType, array[i]);
        }
    }

    public String saveFormat() {
        String output = "";
        for (String type: commandTypeList.keySet()) {
            int size = commandTypeList.get(type).size();
            output = output + type;
            for (int i = 0; i < size; i++) {
                output = output + "/ " + commandTypeList.get(type).get(i);
            }
            output = output + "\n";
        }
        return output;
    }

    public void addCommandList(List<String[]> list) {
        for (String[] command : list) {
            saveCommand(command);
        }

    }
}
