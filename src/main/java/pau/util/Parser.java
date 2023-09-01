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
    public static void parseCommand(String input, TaskList list) {
        if (input.equals("list")) {
            list.checkList();
        } else if (input.startsWith("mark")) {
            list.markTask(input);
        } else if (input.startsWith("unmark")) {
            list.unMarkTask(input);
        } else if (input.startsWith("delete")) {
            list.deleteTask(input);
        } else if (input.startsWith("todo")) {
            list.addToDo(input);
        } else if (input.startsWith("deadline")) {
            list.addDeadline(input);
        } else if (input.startsWith("event")) {
            list.addEvent(input);
        } else if (input.startsWith("find")) {
            list.findTask(input);
        } else {
            System.out.println("can you follow instructions");
        }
    }
}
