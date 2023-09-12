package sam.services;

import sam.exceptions.DukeException;
import sam.services.parser.FileParser;
import sam.tasks.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * Represents the hard disk used to store task list data.
 */
public class Storage {
    private String path;

    public Storage(String path) {
        this.path = path;
    }

    /**
     * Ensure that the hard disk is present in the file directory.
     * Creates the file if not found
     * @return the new file created
     */
    private File createHardDisk() {
        File file = new File(this.path);
        File directory = file.getParentFile();

        if (!directory.exists()) {
            directory.mkdirs();
        }

        return file;
    }

    /**
     * Saves the {@code taskList} data to the storage file.
     *
     * @throws IOException if there were errors converting and/or storing data to file.
     */
    public void saveTasksToFile(TaskList tasks) throws IOException {
        File file = createHardDisk();

        FileWriter fileWriter = new FileWriter(file);
        for (Task task : tasks.getTasks()) {
            fileWriter.write(task.toFileString() + "\n");
        }
        fileWriter.close();
    }

    /**
     * Loads the {@code taskList} data from this storage file into the TaskList.
     *
     * @throws IOException if there were errors reading and/or converting data from file.
     */
    public void loadTasksFromFile(TaskList tasks) throws IOException, DukeException {
        File file = createHardDisk();

        List<String> list = Files.readAllLines(file.toPath());
        for (String taskString : list) {
            Task task = FileParser.parseTaskFromString(taskString);
            tasks.addTask(task);
        }
    }
}
