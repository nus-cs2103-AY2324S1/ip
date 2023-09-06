package duke.parser;

import duke.exceptions.DukeException;
import duke.dataFile.Storage;
import duke.ui.Ui;
import duke.tasks.TaskList;

public class Parser {
    public enum modifyStatus {
        MARK,
        UNMARK
    }

    public enum taskType {
        EVENT,
        DEADLINE,
        TODO
    }

    /**
     * Takes in commands from the user and does an action based on the command.
     *
     * @param nextLine input from the user.
     * @param tasks list of task stored.
     * @param storage storage for the tasks to be housed.
     * @return lets the program know to stop
     */
    public static boolean parseCommands(String nextLine, TaskList tasks, Storage storage) {
        String firstWord = nextLine.split(" ")[0];
        try {
            switch(firstWord) {
            case "bye":
                Ui.bidFarewell();
                return false;
            case "list":
                Ui.printItems(tasks);
                break;
            case "unmark":
                tasks.modifyStatus(modifyStatus.UNMARK, nextLine);
                break;
            case "mark":
                tasks.modifyStatus(modifyStatus.MARK, nextLine);
                break;
            case "delete":
                tasks.deleteItem(nextLine);
                break;
            case "event":
                tasks.addItem(taskType.EVENT, nextLine);
                break;
            case "deadline":
                tasks.addItem(taskType.DEADLINE, nextLine);
                break;
            case "todo":
                tasks.addItem(taskType.TODO, nextLine);
                break;
            case "find":
                tasks.findItems(nextLine);
                break;
            default:
                throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            storage.write(tasks);
        } catch (DukeException e) {
            Ui.printWrapped(e.getMessage());
        }
        return true;
    }
}
