package duke;

import Tasks.Task;

import java.util.ArrayList;

/**
 * This class handles the input from the user and parses them and calls the
 * appropriate methods based on the user input,.
 */
public class Parser {

    public String parse(String input, ArrayList<Task> taskList, TaskExecutor executor) {
        String[] inputParts = input.split(" ", 2);
        String command = inputParts[0];

        switch (command) {
        case "list":
            return executor.listTasks(taskList);
        case "mark":
            return executor.markTask(inputParts, taskList, true);
        case "unmark":
            return executor.markTask(inputParts, taskList, false);
        case "delete":
            return executor.deleteTask(inputParts, taskList);
        case "find":
            return executor.findTasks(inputParts, taskList);
        case "bye":
            return "Bye. Hope to see you again soon!\n";
        default:
            return executor.addTask(inputParts, taskList);
        }
    }

}
