package cringebot.parser;

import cringebot.exceptions.DukeException;
import cringebot.dataFile.Storage;
import cringebot.ui.Ui;
import cringebot.tasks.TaskList;

/**
 * Class for parsing commands made by the user.
 */
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
     * @return lets the program know to stop.
     */
    public static String parseCommands(String nextLine, TaskList tasks, Storage storage) {
        String firstWord = nextLine.split(" ")[0];
        String statement;
        try {
            switch(firstWord) {
            case "bye":
                System.exit(0);
                return Ui.bidFarewell();
            case "list":
                return Ui.printItems(tasks);
            case "unmark":
                statement = tasks.modifyStatus(modifyStatus.UNMARK, nextLine);
                storage.write(tasks);
                return statement;
            case "mark":
                statement = tasks.modifyStatus(modifyStatus.MARK, nextLine);
                storage.write(tasks);
                return statement;
            case "delete":
                statement = tasks.deleteItem(nextLine);
                storage.write(tasks);
                return statement;
            case "event":
                statement = tasks.addItem(taskType.EVENT, nextLine);
                storage.write(tasks);
                return statement;
            case "deadline":
                statement = tasks.addItem(taskType.DEADLINE, nextLine);
                storage.write(tasks);
                return statement;
            case "todo":
                statement = tasks.addItem(taskType.TODO, nextLine);
                storage.write(tasks);
                return statement;
            case "find":
                return tasks.findItems(nextLine);
            default:
                throw new DukeException(":(( OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
