package cringebot.parser;

import cringebot.datafile.Storage;
import cringebot.exceptions.CringeBotException;
import cringebot.tasks.TaskList;
import cringebot.ui.Ui;

/**
 * Class for parsing commands made by the user.
 */
public class Parser {
    /**
     * Enum for marking and unmarking tasks.
     */
    public enum ModifyStatus {
        MARK,
        UNMARK
    }

    /**
     * Enum for the type of task.
     */
    public enum TaskType {
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
        assert nextLine != null : "nextLine should not be null";

        String firstWord = nextLine.split(" ")[0];
        String statement;

        // Check if a substring is in a string
        boolean isRecurring = nextLine.contains("/recurring");

        try {
            switch(firstWord) {
            case "bye":
                System.exit(0);
                return Ui.bidFarewell();
            case "list":
                return Ui.printItems(tasks);
            case "unmark":
                statement = tasks.modifyStatus(ModifyStatus.UNMARK, nextLine);
                storage.writeToFile(tasks);
                return statement;
            case "mark":
                statement = tasks.modifyStatus(ModifyStatus.MARK, nextLine);
                storage.writeToFile(tasks);
                return statement;
            case "delete":
                statement = tasks.deleteItem(nextLine);
                storage.writeToFile(tasks);
                return statement;
            case "event":
                if (isRecurring) {
                    throw new CringeBotException("Oops! I can't do recurring events :((");
                }
                statement = tasks.addItem(TaskType.EVENT, nextLine);
                storage.writeToFile(tasks);
                return statement;
            case "deadline":
                statement = isRecurring
                        ? tasks.addRecurringItems(TaskType.DEADLINE, nextLine)
                        : tasks.addItem(TaskType.DEADLINE, nextLine);
                storage.writeToFile(tasks);
                return statement;
            case "todo":
                if (isRecurring) {
                    throw new CringeBotException("Oops! I can't do recurring todos :((");
                }
                statement = tasks.addItem(TaskType.TODO, nextLine);
                storage.writeToFile(tasks);
                return statement;
            case "find":
                return tasks.findItems(nextLine);
            default:
                throw new CringeBotException("OOPS!!! I'm sorry, but I don't know what that means. :(( ");
            }
        } catch (CringeBotException e) {
            return e.getMessage();
        }
    }
}
