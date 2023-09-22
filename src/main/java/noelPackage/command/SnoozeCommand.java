package noelPackage.command;

import noelPackage.helper.Storage;
import noelPackage.helper.Tasklist;

public class SnoozeCommand extends Command{

    private final String command;

    public SnoozeCommand(String command) {
        this.command = command;
    }
    @Override
    public String execute(Tasklist tasks, Storage storage) {

        assert command != null : "Should have an input!";

        String[] commandInputs = this.command.split("snooze ");

        if (commandInputs.length == 1) {
            System.out.println("No keywords given!");
        } else {

            commandInputs = commandInputs[1].split(" ");
            int numOfTask = tryParse(commandInputs[0]);
            if (numOfTask == -99999) {
                return "Invalid Number given!";
            } else {

                if (commandInputs.length == 7) {

                    String returnStr =  parseEvent(commandInputs, tasks, numOfTask - 1);
                    storage.writeToFile(tasks.getTaskAsList());
                    return returnStr;

                } else if (commandInputs.length == 4) {

                    String returnStr =  parseDeadline(commandInputs,tasks, numOfTask - 1, storage);
                    storage.writeToFile(tasks.getTaskAsList());
                    return returnStr;

                } else {
                    return "Insufficient Commands Given!";
                }
            }
        }
        return "";
    }

    public String parseEvent(String[] commandInputs, Tasklist tasks, int numOfTask) {
        String start = commandInputs[2] + " " + commandInputs[3];
        String end = commandInputs[5] + " " + commandInputs[6];
        return tasks.updateEvent(numOfTask, start, end);
    }

    public String parseDeadline(String[] commandList, Tasklist tasks, int index, Storage storage) {
        String date = commandList[2] + " " + commandList[3];
        String returnStr = tasks.updateDeadline(index, date);
        storage.writeToFile(tasks.getTaskAsList());
        return returnStr;
    }

    public static int tryParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return -99999;
        }
    }
}
