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
    public void execute(String command) {
        switch (getCommandWord(command)) {
            case "bye":
                Ui.bye();
                break;
            case "list":
                System.out.println(tasks.list());
                break;
            case "mark":
                tasks.mark(getIndex(command));
                break;
            case "unmark":
                tasks.unmark(getIndex(command));
                break;
            case "todo":
                toDoParser(command);
                break;
            case "deadline":
                deadlineParser(command);
                break;
            case "event":
                eventParser(command);
                break;
            case "delete":
                tasks.delete(getIndex(command));
                break;
            case "overdue":
                System.out.println(tasks.overdue());
                break;
            case "dueby":
                System.out.println(tasks.dueBy(command));
                break;
            case "save":
                storage.save(tasks, removeCommandWord(command));
                break;
            case "find":
                System.out.println(tasks.find(removeCommandWord(command)));
                break;


            default:
                EpochMindException e = new EpochMindException("There is no such command in the Arcana of Knowledge");
                System.out.println(e);
                break;
        }
    }

    /**
     * Parse command for todo creation
     *
     * @param command User Input
     */
    public void toDoParser(String command) {
        String restOfCommand = Parser.removeCommandWord(command);
        if (restOfCommand.equals("")) {
            EpochMindException e = new EpochMindException("Thou hast not specified a task");
            System.out.println(e);
        } else {
            tasks.todo(restOfCommand);
        }
    }

    /**
     * Parse command for deadline creation
     *
     * @param command User Input
     */
    public void deadlineParser(String command) {
        String restOfCommand = Parser.removeCommandWord(command);
        int endIndex = restOfCommand.indexOf("/by ");
        if (endIndex == -1) {
            EpochMindException e = new EpochMindException("The Mind needs a deadline");
            System.out.println(e);
        } else {
            String taskDescription = restOfCommand.substring(0, endIndex).trim();
            if (taskDescription.trim().equals("")) {
                EpochMindException e = new EpochMindException("Thou hast not specified a task");
                System.out.println(e);
            } else {
                int deadlineIndex = restOfCommand.indexOf("/by ") + 3;
                String deadlineString = restOfCommand.substring(deadlineIndex);
                tasks.deadline(taskDescription, deadlineString);
            }
        }
    }

    /**
     * Add an event task
     *
     * @param command String obtained from scanner
     */
    public void eventParser(String command) {
        String restOfCommand = Parser.removeCommandWord(command);
        int fromIndex = restOfCommand.indexOf("/from");
        if (fromIndex == -1) {
            EpochMindException e = new EpochMindException("The Mind needs a start time");
            System.out.println(e);
        } else {
            String description = restOfCommand.substring(0, fromIndex).trim();
            if (description.equals("")) {
                EpochMindException e = new EpochMindException("Thou hast not specified a task");
                System.out.println(e);
            } else {
                fromIndex = restOfCommand.indexOf("/from") + "/from".length();
                int toIndex = restOfCommand.indexOf("/to");
                if (toIndex == -1) {
                    EpochMindException e = new EpochMindException("The Mind needs a end time");
                    System.out.println(e);
                } else {
                    String startString = restOfCommand.substring(fromIndex, toIndex).trim();
                    toIndex = restOfCommand.indexOf("/to") + "/to".length();
                    String endString = restOfCommand.substring(toIndex);
                    tasks.event(description, startString, endString);
                }
            }
        }
    }
}
