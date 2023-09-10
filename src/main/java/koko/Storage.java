package koko;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveTasksToFile(TaskList taskListToSave) throws IOException {
        File directory = new File("./data");
        if (!directory.exists()) {
            directory.mkdir();
        }

        FileWriter fw = new FileWriter(filePath);
        fw.write(taskListToSave.toStringForFile());
        fw.close();

    }

    public TaskList loadTasksFromFile() throws FileNotFoundException, DukeException {
        File file = new File(filePath);

        Scanner scanner = new Scanner(file);

        ArrayList<Task> result = new ArrayList<>();

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            try {
                Task task = Task.fromFileFormat(line);
                if (task != null) {
                    result.add(task);
                }
            } catch (DukeException dukeException) {
                throw dukeException;
            } catch (Exception e) {
                throw new DukeException("Error while parsing data file -- possibly corrupt?"
                        + "File will be overwritten if you proceed.");
            }
        }
        return new TaskList(result);
    }
}
