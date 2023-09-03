package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeDatabaseException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Handles the loading and storing tasks to a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param filePath The file path for storing task data.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from teh specified file path.
     *
     * @return The list of tasks loaded.
     * @throws DukeDatabaseException If there is a problem with creating the database.
     */
    public ArrayList<Task> loadData() throws DukeDatabaseException {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        File file = new File(this.filePath);
        boolean createdFile = false;

        if (!file.exists()) {
            createFile();
            createdFile = true;
        }

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                loadedTasks.add(readEntry(line));
            }

            scanner.close();

            if (createdFile) {
                return new ArrayList<>();
            } else {
                return loadedTasks;
            }
        } catch (FileNotFoundException e) {
            this.createFile();
        }

        return new ArrayList<>();
    }

    /**
     * Creates the storage txt file and its directory.
     *
     * @throws DukeDatabaseException If there is a problem with creating the file.
     */
    private void createFile() throws DukeDatabaseException {
        File file = new File(this.filePath);
        File directory = new File(file.getParent());

        if (!directory.exists()) {
            directory.mkdirs();
        }

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new DukeDatabaseException();
        }
    }

    /**
     * Reads the entry from the txt file and converts to a Task object.
     *
     * @param entry The entry to be read.
     * @return The corresponding Task object read from the file.
     */
    private Task readEntry(String entry) {
        String[] parts = entry.split(" \\| ");
        Task taskToAdd = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        switch (parts[0]) {
        case "T":
            taskToAdd = new Todo(parts[2]);
            break;
        case "D":
            taskToAdd = new Deadline(parts[2], LocalDateTime.parse(parts[3], formatter));
            break;
        case "E":
            taskToAdd = new Event(parts[2], LocalDateTime.parse(parts[3], formatter));
            break;
        default:
            break;
        }

        if (parts[1].equals("1")) {
            taskToAdd.markAsDone();
        }
        return taskToAdd;
    }

    /**
     * Saves the tasks from the TaskList to the storage txt file.
     *
     * @param taskList The TaskList containing the tasks to be saved.
     * @throws IOException If there is an issue writing to the file.
     */
    public void saveData(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        for (int i = 0; i < taskList.getLength(); i++) {
            Task task = taskList.getTask(i);
            String line = task.toFileString();
            fw.write(line + System.lineSeparator());
        }

        fw.close();
    }
}
