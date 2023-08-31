package Pau.util;

import Pau.Pau;
import Pau.task.TaskList;

public class Parser {
    public static boolean parseCommand(String input, TaskList list) {
        if (input.equals("bye")) {
            return false;
        } else if (input.equals("list")) {
            list.checkList();
            return true;
        } else if (input.startsWith("mark")) {
            list.markTask(input);
            return true;
        } else if (input.startsWith("unmark")) {
            list.unMarkTask(input);
            return true;
        } else if (input.startsWith("delete")) {
            list.deleteTask(input);
            return true;
        } else if (input.startsWith("todo")) {
            list.addsToDo(input);
            return true;
        } else if (input.startsWith("deadline")) {
            list.addDeadline(input);
            return true;
        } else if (input.startsWith("event")) {
            list.addEvent(input);
            return true;
        } else {
            Pau.invalidCommand();
            return true;
        }
    }
}
