package duke;

import java.util.ArrayList;

/**
 * This class handles the input from the user and parses them and calls the
 * appropriate methods based on the user input,.
 */
public class Parser {

    public static void parse(String input, ArrayList<duke.Task> taskList) {
        String[] inputParts = input.split(" ", 2);
        String command = inputParts[0];

        switch (command) {
        case "list":
            TaskList.listTasks(taskList);
            break;
        case "mark":
            TaskList.markTask(inputParts, taskList, true);
            break;
        case "unmark":
            TaskList.markTask(inputParts, taskList, false);
            break;
        case "delete":
            TaskList.deleteTask(inputParts, taskList);
            break;
        case "find":
            TaskList.findTasks(inputParts, taskList);
            break;
        default:
            TaskList.addTask(inputParts, taskList);
            break;
        }
    }

}
