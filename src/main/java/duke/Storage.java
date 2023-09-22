package duke;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Storage class to retrieve and write the task list
public class Storage {
    public String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    // if the file cant be found in the filepath, an exception will be thrown
    public TaskList load() throws DukeException {
        TaskList tasks = new TaskList();
        File file = new File(filePath);
        if (file.exists()) {
            tasks = TaskReader.readTasksFromFile(filePath);
            System.out.println(tasks);
        }

        return tasks;
    }
    // saves the task list at the file path specified
    public void save(TaskList tasks, String filePath) {
        TaskWriter.writeTasksToFile(tasks, filePath);
    }
}
