package duke.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import duke.alias.AliasMap;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;


/**
 * The `Storage` class handles reading and writing tasks to and from a data
 * file.
 */
public class Storage {

    private final File dataFile;
    private final File aliasFile;

    /**
     * Constructs a `Storage` object with the specified file path.
     *
     * @param folderPath The path to the data file used for storage.
     */
    public Storage(String folderPath) {
        File dataFile = new File(folderPath + "/data.txt");
        File aliasFile = new File(folderPath + "/alias.txt");

        if (!dataFile.exists()) {
            dataFile = createFile(dataFile);
        }
        if (!aliasFile.exists()) {
            aliasFile = createFile(aliasFile);
        }

        this.dataFile = dataFile;
        this.aliasFile = aliasFile;
    }

    private File createFile(File file) {
        File parent = file.getParentFile();

        if (!parent.exists()) {
            parent.mkdirs();
        }

        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Error while creating file: " + e);
        }
        return file;
    }

    /**
     * Loads tasks from the data file and returns them as an `ArrayList` of `Task`
     * objects.
     *
     * @return An `ArrayList` of `Task` objects representing the tasks loaded from
     *         the data file.
     * @throws DukeException If an error occurs while reading from the data file.
     */
    public ArrayList<Task> loadData() throws DukeException {
        ArrayList<Task> items = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(dataFile));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lineArr = line.split("\\|");

                String taskType = lineArr[0];
                boolean isDone = Integer.parseInt(lineArr[1]) == 1;
                String name = lineArr[2];
                switch (taskType) {
                case "T":
                    items.add(new ToDo(name, isDone));
                    break;
                case "D":
                    String by = lineArr[3];
                    items.add(new Deadline(name, LocalDateTime.parse(by), isDone));
                    break;
                case "E":
                    String from = lineArr[3];
                    String to = lineArr[4];
                    items.add(new Event(name, LocalDateTime.parse(from), LocalDateTime.parse(to), isDone));
                    break;
                default:
                    throw new DukeException("Unknown task type in datafile.");
                }
            }
            reader.close();
        } catch (IOException e) {
            throw new DukeException("An error occurred while reading from the data file.");
        }
        return items;
    }

    /**
     * Loads aliases from the alias file and returns them as an `HashMap` of
     * Strings to Strings.
     *
     * @return A `HashMap` of Strings to Strings representing the aliases loaded from
     *         the alias file.
     * @throws DukeException If an error occurs while reading from the alias file.
     */
    public HashMap<String, String> loadAlias() throws DukeException {
        HashMap<String, String> aliasMap = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(aliasFile));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lineArr = line.split("=");
                String alias = lineArr[0];
                String fullCommand = lineArr[1];
                aliasMap.put(alias, fullCommand);
            }
            reader.close();
        } catch (IOException e) {
            throw new DukeException("An error occurred while reading from the alias file.");
        }
        return aliasMap;
    }

    /**
     * Writes tasks to the data file.
     *
     * @param items An `ArrayList` of `Task` objects representing the tasks to be
     *              saved.
     * @throws DukeException If an error occurs while writing to the data file.
     */
    public void writeData(TaskList items) throws DukeException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile));
            for (Task t : items) {
                writer.write(t.toDataString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("An error occurred while writing to the data file.");
        }
    }

    /**
     * Writes aliases to the alias file.
     *
     * @param aliasMap A `HashMap` of Strings to Strings representing the aliases to be
     *              saved.
     * @throws DukeException If an error occurs while writing to the alias file.
     */
    public void writeAlias(AliasMap aliasMap) throws DukeException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(aliasFile));

            for (Map.Entry<String, String> entry : aliasMap) {
                String alias = entry.getKey();
                String command = entry.getValue();
                writer.write(alias + "=" + command);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("An error occurred while writing to the alias file.");
        }
    }
}
