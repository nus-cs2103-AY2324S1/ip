package duke;

import duke.command.DukeException;
import duke.command.Parser;
import duke.task.Task;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasksFromFile() throws DukeException {
        ArrayList<Task> tasks = new ArrayList();

        try {
            File file = new File(this.filePath);
            if (!file.exists()) {
                return tasks;
            } else {
                BufferedReader reader = new BufferedReader(new FileReader(file));

                String line;
                while((line = reader.readLine()) != null) {
                    Task task = Parser.parseFileLine(line);
                    tasks.add(task);
                }

                reader.close();
                return tasks;
            }
        } catch (IOException var6) {
            throw new DukeException("Error loading tasks from file: " + var6.getMessage());
        }
    }

    public void saveTasksToFile(List<Task> tasks) throws DukeException {
        try {
            FileWriter writer = new FileWriter(this.filePath);
            Iterator var3 = tasks.iterator();

            while(var3.hasNext()) {
                Task task = (Task)var3.next();
                writer.write(task.toFileString() + "\n");
            }

            writer.close();
        } catch (IOException var5) {
            throw new DukeException("Error saving tasks to file: " + var5.getMessage());
        }
    }
}
