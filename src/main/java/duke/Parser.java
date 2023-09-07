package duke;

import java.util.ArrayList;

import tasks.Task;
import workers.AddWorker;
import workers.DeleteWorker;
import workers.FindWorker;
import workers.ListWorker;
import workers.MarkWorker;
import workers.TaskWorker;

/**
 * This class handles the input from the user and parses them and calls the
 * appropriate methods based on the user input,.
 */
public class Parser {

    /**
     * Parses through the given input and creates the appropriate worker class and
     * returns the output of the worker class working on the Task.
     * @param input
     * @param taskList
     * @return
     */
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
