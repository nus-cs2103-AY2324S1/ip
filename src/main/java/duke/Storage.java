package duke;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    public String filePath;

    public Storage(String filePath) {

        this.filePath = filePath;
    }

    public TaskList load() throws DukeException {
        TaskList tasks = new TaskList();
        File file = new File(filePath);
        if (file.exists()) {
            tasks = TaskReader.readTasksFromFile(filePath);
            System.out.println(tasks);
        }

        return tasks;
    }

    public void save(TaskList tasks, String filePath) {
        TaskWriter.writeTasksToFile(tasks, filePath);
    }
}
