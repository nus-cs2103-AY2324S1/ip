package pau.util;

import pau.task.TaskList;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    /**
     * Parses the command that user inputs.
     *
     * @param input Command that user inputs.
     * @param list List of the tasks.
     */
    public static String parseCommand(String input, TaskList list) {
        if (input.equals("list")) {
            return list.checkList();
        } else if (input.startsWith("mark ")) {
            return list.markTask(input);
        } else if (input.startsWith("unmark ")) {
            return list.unMarkTask(input);
        } else if (input.startsWith("delete ")) {
            return list.deleteTask(input);
        } else if (input.startsWith("todo ")) {
            return list.addToDo(input);
        } else if (input.startsWith("deadline ")) {
            return list.addDeadline(input);
        } else if (input.startsWith("event ")) {
            return list.addEvent(input);
        } else if (input.startsWith("find ")) {
            return list.findTask(input);
        } else if (input.startsWith("help")) {
            return list.help();
        } else if (input.startsWith("clear")) {
            return list.clear();
        } else {
            return "can you follow instructions";
        }
    }
}
