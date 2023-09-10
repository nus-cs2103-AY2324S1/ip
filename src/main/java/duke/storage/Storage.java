package duke.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import duke.exceptions.DukeException;
import duke.exceptions.DukeIoException;
import duke.tasks.TaskList;
import duke.tasks.TaskType;
import duke.ui.CliUi;

/**
 * The storage is used to save and load tasks from the local disk.
 */
public class Storage {

    /** The path to the save file. */
    private final String path;

    /**
     * Creates a new Storage object.
     *
     * @param path The path to the save file.
     * @throws DukeException If there is an error creating the save file.
     */
    public Storage(String path) throws DukeException {
        this.path = path;
        File file = new File(path);
        if (!file.exists()) {
            CliUi.printlns(new String[] { "...No saved tasks found.", "Creating new save file for you..." });
            try {
                file.getParentFile().mkdirs();
                FileWriter writer = new FileWriter(file);
                writer.close();
            } catch (IOException e) {
                throw new DukeIoException("Error creating save file: " + e);
            }
        }
    }

    /**
     * Loads tasks from the save file into the given task list.
     *
     * @param tasklist The task list to be used.
     * @throws DukeException If there is an error loading tasks from the save file.
     */
    public void load(TaskList tasklist) throws DukeException {
        try {
            boolean isEmpty = Files.size(Path.of(path)) == 0;
            if (isEmpty) {
                CliUi.println("Save file empty, you're good to go.");
                return;
            }

            CliUi.println("Found some old tasks, replaying some commands...");
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] args = line.split("\\|\\|");
                TaskType type = TaskType.valueOf(args[0]);
                boolean isDone = args[1].equals("1");
                tasklist.addTask(type, args[2]);
                if (isDone) {
                    tasklist.markTaskDone(tasklist.size());
                }
            }
            reader.close();
        } catch (IOException e) {
            throw new DukeIoException("Error loading tasks from save file: " + e);
        }
    }

    /**
     * Saves tasks from the given task list into the save file.
     *
     * @param tasklist The task list to be used.
     * @throws DukeException If there is an error saving tasks to the save file.
     */
    public void save(TaskList tasklist) throws DukeException {
        assert Files.exists(Paths.get(this.path)) : "Save file should exist before saving tasks to file.";
        try {
            String content = "";
            FileWriter fileWriter = new FileWriter(path, false);

            for (int i = 0; i < tasklist.size(); i++) {
                content += tasklist.getTask(i).getSaveFormat();
            }

            fileWriter.write(content);
            fileWriter.close();
            CliUi.println("...Successfully saved your tasks!");
        } catch (IOException e) {
            throw new DukeIoException("Error saving tasks to local disk: " + e);
        }
    }
}
