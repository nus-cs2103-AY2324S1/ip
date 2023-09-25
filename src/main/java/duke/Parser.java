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
 * appropriate methods based on the user input.
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
        String command = inputParts[0].toLowerCase();
        TaskWorker worker;
        String output = "";
        switch (command) {
        case "list":
            worker = new ListWorker();
            output = worker.work(taskList);
            break;
        case "mark":
            worker = new MarkWorker();
            output = worker.work(inputParts, taskList, true);
            break;
        case "unmark":
            worker = new MarkWorker();
            output = worker.work(inputParts, taskList, false);
            break;
        case "delete":
            worker = new DeleteWorker();
            output = worker.work(inputParts, taskList);
            break;
        case "find":
            worker = new FindWorker();
            output = worker.work(inputParts, taskList);
            break;
        case "bye":
            System.exit(0);
            break;
        default:
            worker = new AddWorker();
            output = worker.work(inputParts, taskList);
            break;
        }
        assert !output.isEmpty();
        return output;
    }

}
