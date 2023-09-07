package duke;

import Tasks.Task;

import java.util.ArrayList;

/**
 * Where the chatBot begins and ends. Uses has-a relationship
 * with other classes.
 */
public class Iris {
    private ArrayList<Task> taskList;
    //private TaskExecutor executor = new TaskExecutor();
    private Parser parser = new Parser();
    private Storage storage = new Storage();

    public Iris() {
        taskList = storage.loadTasksFromFile();
    }

    public String getResponse(String text) {
        String output = parser.parse(text, taskList);
        storage.updateTasksFile(taskList);
        return output;
    }

}