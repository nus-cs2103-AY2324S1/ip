package duke;

import java.util.ArrayList;

import tasks.Task;

/**
 * Where the chatBot begins and ends. Uses has-a relationship
 * with other classes.
 */
public class Iris {
    private ArrayList<Task> taskList;
    private Parser parser = new Parser();
    private Storage storage = new Storage();

    public Iris() {
        taskList = storage.loadTasksFromFile();
    }

    public String getResponse(String text) {
        String output = parser.parse(text, taskList);
        assert !output.equals("");
        storage.updateTasksToFile(taskList);
        return output;
    }
}
