package duke.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import duke.exception.KoraException;

/**
 * Command list class that stores command names and types.
 */
public class CommandList {
    private HashMap<String, String> commandNameList;
    private HashMap<String, ArrayList<String>> commandTypeList;

    /**
     * Class constructor of CommandList.
     */
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

    /**
     * Adds Command name to command name list.
     * @param commandType Type of Command.
     * @param commandName Name of Command.
     */
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

    /**
     * Deletes Command name from command name list.
     * @param commandType Type of Command.
     * @param commandName Name of Command.
     * @throws KoraException When the only command name gets called to be deleted.
     */
    public void deleteCommandName(String commandType, String commandName) throws KoraException {
        commandNameList.remove(commandName);
        ArrayList<String> nameList = commandTypeList.get(commandType);
        nameList.remove(commandName);
        if (nameList.isEmpty()) {
            throw new KoraException("Omo! You won't have any available command name for: " + commandType);
        }
        commandTypeList.put(commandType, nameList);
    }

    /**
     * Returns Command type based on the Command name.
     * @param commandName Name of Command.
     * @return Type of Command.
     */
    public String getCommandType(String commandName) {
        return commandNameList.get(commandName);
    }

    /**
     * Lists all Command name in the command name list.
     * @return String of command name list.
     */
    public String listAllCommandName() {
        String output = "";
        for (String type: commandTypeList.keySet()) {
            output = output + type + ": " + commandTypeList.get(type).toString() + "\n";
        }
        return output;
    }

    /**
     * Saves array of Command names to command name list.
     * @param array String array pf Command names.
     */
    public void saveCommand(String[] array) {
        String commandType = array[0];
        int size = array.length;

        for (int i = 1; i < size; i++) {
            addCommandName(commandType, array[i]);
        }
    }

    /**
     * Returns formatted String representation of command name list.
     * @return String of formatted command name list.
     */
    public String saveFormat() {
        String output = "";
        for (String type: commandTypeList.keySet()) {
            int size = commandTypeList.get(type).size();
            output = output + type;
            for (int i = 0; i < size; i++) {
                output = output + " / " + commandTypeList.get(type).get(i);
            }
            output = output + "\n";
        }
        return output;
    }

    /**
     * Adds list of String arrays that contain Command names.
     * @param list List of array of String that contains Command names.
     */
    public void addCommandList(List<String[]> list) {
        for (String[] command : list) {
            saveCommand(command);
        }

    }
}
