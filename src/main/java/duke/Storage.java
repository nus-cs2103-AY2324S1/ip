package duke;

import duke.command.DukeException;
import duke.command.Parser;
import duke.task.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasksFromFile() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return tasks; // If the file doesn't exist yet, no need to load tasks
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Parser.parseFileLine(line);
                tasks.add(task);
            }
            reader.close();
        } catch (IOException e) {
            throw new DukeException("Error loading tasks from file: " + e.getMessage());
        }

        return tasks;
    }

    public void saveTasksToFile(List<Task> tasks) throws DukeException {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                writer.write(task.toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Error saving tasks to file: " + e.getMessage());
        }
    }
}