package util;

import exception.EpochMindException;
import task.TaskList;

import static java.lang.Integer.parseInt;

/**
 * Class that parses the commands given by user
 */
public class Parser {
    public TaskList tasks;
    public Storage storage;

    public Parser(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Helper function to remove the first command word
     *
     * @param command String obtained from scanner
     * @return String without first command word
     */
    public static String removeCommandWord(String command) {
        int firstSpaceIndex = command.indexOf(' ');

        if (firstSpaceIndex == -1 || firstSpaceIndex == command.length() - 1) {
            return "";
        }
        // Extract the substring starting from the position after the first space
        return command.substring(firstSpaceIndex + 1).trim();
    }

    public static String getCommandWord(String command) {
        return command.trim().toLowerCase().split(" ")[0];
    }

    /**
     * Helper function to get the index from command
     *
     * @param command
     * @return
     */
    public static int getIndex(String command) {
        String[] commandList = command.trim().toLowerCase().split(" ");
        if (commandList.length == 1) {
            EpochMindException e = new EpochMindException("Thou hast forgotten to specify thine index");
            System.out.println(e);
            return -1;
        } else {
            try {
                int index = parseInt(commandList[1]);
                return index;
            } catch (Exception e) {
                System.out.println("Thou hast given an invalid index");
            }
            return -1;
        }
    }

    /**
     * Executes the command
     *
     * @param command User input
     */
    public String execute(String command) {
        switch (getCommandWord(command)) {
            case "bye":
                return Ui.bye();
            case "list":
                return tasks.list();
            case "mark":
                return tasks.mark(getIndex(command));
            case "unmark":
                return tasks.unmark(getIndex(command));
            case "todo":
                return toDoParser(command);
            case "deadline":
                return deadlineParser(command);
            case "event":
                return eventParser(command);
            case "delete":
                return tasks.delete(getIndex(command));
            case "overdue":
                return tasks.overdue();
            case "dueby":
                return tasks.dueBy(command);
            case "save":
                return storage.save(tasks, removeCommandWord(command));
            case "find":
                return tasks.find(removeCommandWord(command));


            default:
                EpochMindException e = new EpochMindException("There is no such command in the Arcana of Knowledge");
                return e.toString();
        }
    }

    /**
     * Parse command for todo creation
     *
     * @param command User Input
     */
    public String toDoParser(String command) {
        String restOfCommand = Parser.removeCommandWord(command);
        if (restOfCommand.equals("")) {
            EpochMindException e = new EpochMindException("Thou hast not specified a task");
            return e.toString();
        } else {
            return tasks.todo(restOfCommand);
        }
    }

    /**
     * Parse command for deadline creation
     *
     * @param command User Input
     */
    public String deadlineParser(String command) {
        String restOfCommand = Parser.removeCommandWord(command);
        int endIndex = restOfCommand.indexOf("/by ");
        if (endIndex == -1) {
            EpochMindException e = new EpochMindException("The Mind needs a deadline");
            return e.toString();
        } else {
            String taskDescription = restOfCommand.substring(0, endIndex).trim();
            if (taskDescription.trim().equals("")) {
                EpochMindException e = new EpochMindException("Thou hast not specified a task");
                return e.toString();
            } else {
                int deadlineIndex = restOfCommand.indexOf("/by ") + 3;
                String deadlineString = restOfCommand.substring(deadlineIndex);
                return tasks.deadline(taskDescription, deadlineString);
            }
        }
    }

    /**
     * Add an event task
     *
     * @param command String obtained from scanner
     */
    public String eventParser(String command) {
        String restOfCommand = Parser.removeCommandWord(command);
        int fromIndex = restOfCommand.indexOf("/from");
        if (fromIndex == -1) {
            EpochMindException e = new EpochMindException("The Mind needs a start time");
            return e.toString();
        } else {
            String description = restOfCommand.substring(0, fromIndex).trim();
            if (description.equals("")) {
                EpochMindException e = new EpochMindException("Thou hast not specified a task");
                return e.toString();
            } else {
                fromIndex = restOfCommand.indexOf("/from") + "/from".length();
                int toIndex = restOfCommand.indexOf("/to");
                if (toIndex == -1) {
                    EpochMindException e = new EpochMindException("The Mind needs a end time");
                    return e.toString();
                } else {
                    String startString = restOfCommand.substring(fromIndex, toIndex).trim();
                    toIndex = restOfCommand.indexOf("/to") + "/to".length();
                    String endString = restOfCommand.substring(toIndex);
                    return tasks.event(description, startString, endString);
                }
            }
        }
    }
}
