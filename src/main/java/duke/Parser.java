package duke;

import Tasks.Task;
import Workers.*;
import javafx.application.Platform;

import java.util.ArrayList;

/**
 * This class handles the input from the user and parses them and calls the
 * appropriate methods based on the user input,.
 */
public class Parser {

    public String parse(String input, ArrayList<Task> taskList) {
        String[] inputParts = input.split(" ", 2);
        String command = inputParts[0];
        TaskWorker worker;

        switch (command) {
        case "list":
            worker = new ListWorker();
            return worker.work(taskList);
        case "mark":
            worker = new MarkWorker();
            return worker.work(inputParts, taskList, true);
        case "unmark":
            worker = new MarkWorker();
            return worker.work(inputParts, taskList, false);
        case "delete":
            worker = new DeleteWorker();
            return worker.work(inputParts, taskList);
        case "find":
            worker = new FindWorker();
            return worker.work(inputParts, taskList);
        case "bye":
            return "Bye. Hope to see you again soon!\n";
        default:
            worker = new AddWorker();
            return worker.work(inputParts, taskList);
        }
    }

}
