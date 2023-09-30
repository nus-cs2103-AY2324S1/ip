package veneto.parser;

import veneto.command.*;
import veneto.exceptions.VenetoException;
import veneto.exceptions.VenetoOperateException;
import veneto.task.Deadline;
import veneto.task.Event;
import veneto.task.Task;
import veneto.task.ToDo;

import java.time.format.DateTimeParseException;

public class Parser {
    /* Fields */
    public static final int FIND_COMMAND_INDEX = 5;
    public static final int TODO_DESCRIPTION_INDEX = 5;
    public static final int DEADLINE_DESCRIPTION_INDEX = 9;
    public static final int DEADLINE_START_INDEX = 3;
    public static final int EVENT_DESCRIPTION_INDEX = 5;
    public static final int EVENT_START_INDEX = 5;
    public static final int EVENT_END_INDEX = 3;

    /* Methods */
    /**
     * translate user put to commands that need to operate
     * @param text the input from user
     * @return the Command that the user wants to operate
     */
    public static Command translateCommand(String text) {
        String[] texts = text.split(" ");
        String command = texts[0].toLowerCase();
        switch (command) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(Integer.parseInt(texts[1]), 1);
            case "unmark":
                return new MarkCommand(Integer.parseInt(texts[1]), 0);
            case "todo":
                return prepareAdd(text, 1);
            case "deadline":
                return prepareAdd(text, 2);
            case "event":
                return prepareAdd(text, 3);
            case "delete":
                return new DeleteCommand(Integer.parseInt(texts[1]));
            case "find":
                return prepareFind(text);
            default:
                throw new VenetoException("Invalid Command");
        }
    }

    /**
     * generate FindCommand according to the user input
     * @param text the details about the task
     * @return the command that the user want to operate
     */
    private static Command prepareFind(String text) {
        String keyword = text.substring(FIND_COMMAND_INDEX);
        return new FindCommand(keyword);
    }

    /**
     * generate AddCommand according to the user input
     * @param text the details about the task
     * @param funcId indicates the type of task
     * @return the command that the user want to operate
     */
    private static Command prepareAdd(String text, int funcId) {
        try {
            String[] texts = text.split("/");
            for (int i = 0; i < texts.length; i++) {
                texts[i] = texts[i].trim();
            }
            Task newTask = null;
            String description;
            switch (funcId) {
                case 1:
                    if (texts.length != 1) {
                        throw new VenetoOperateException("add");
                    }
                    description = texts[0].substring(TODO_DESCRIPTION_INDEX);
                    newTask = new ToDo(description);
                    break;
                case 2:
                    if (texts.length != 2) {
                        throw new VenetoOperateException("add");
                    }
                    description = texts[0].substring(DEADLINE_DESCRIPTION_INDEX);
                    String deadline = texts[1].substring(DEADLINE_START_INDEX);
                    newTask = new Deadline(description, deadline);
                    break;
                case 3:
                    if (texts.length != 3) {
                        throw new VenetoOperateException("add");
                    }
                    description = texts[0].substring(EVENT_DESCRIPTION_INDEX);
                    String start = texts[1].substring(EVENT_START_INDEX);
                    String end = texts[2].substring(EVENT_END_INDEX);
                    newTask = new Event(description, start, end);
                    break;
                default:
                    assert false : "invalid funcId";
            }
            return new AddCommand(newTask);
        } catch (DateTimeParseException e) {
            throw new VenetoOperateException("add");
        }
    }
}
