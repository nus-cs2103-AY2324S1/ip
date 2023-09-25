package thea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Represents a class that deals with saving and loading data.
 */
public class Storage {
    private String fileName;

    /**
     * Constructs a new Storage object.
     *
     * @param fileName name of file containing past data.
     */
    public Storage(String fileName) {
        this.fileName = fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return this.fileName;
    }

    /**
     * Loads data from file.
     *
     * @return a past saved task list from last time use of program,an empty task list if not present.
     * @throws FileCorruptedException when the data is not in expected format.
     */
    public ArrayList<Task> retrieveTasks() throws FileCorruptedException {
        String currentDir = System.getProperty("user.dir");
        Path dataDirPath = Paths.get(currentDir, "data");
        Path path = Paths.get(currentDir, "data", this.fileName);
        if (!Files.exists(dataDirPath) || !Files.exists(path)) {
            return new ArrayList<>();
        }
        ArrayList<Task> retrievedTasks = new ArrayList<>();
        try (BufferedReader bufferReader = Files.newBufferedReader(path)) {
            String line = bufferReader.readLine();
            while (line != null) {
                Task task = parseTask(line);
                retrievedTasks.add(task);
                line = bufferReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return retrievedTasks;
    }

    /**
     * Parses data from file.
     *
     * @return parsed task.
     * @throws FileCorruptedException when the data is not in expected format.
     */
    private static Task parseTask(String line) throws FileCorruptedException {
        String[] splitLine = line.split(" [|] ");
        Task task;
        switch (splitLine[0]) {
        case "T":
            task = new ToDo(splitLine[2]);
            break;
        case "D":
            task = new Deadline(splitLine[2], splitLine[3]);
            break;
        case "E":
            task = new Event(splitLine[2], splitLine[3], splitLine[4]);
            break;
        default:
            throw new FileCorruptedException("Unexpected File Format Found. File might be corrupted.");
        }
        if (splitLine[1].equals("1")) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Saves the current task list to the file.
     * If the file does not exist, makes a new file with
     * file name as specified during the creation of the Storage object.
     *
     * @param tasks list of current tasks.
     */
    public void saveTaskList(TaskList tasks) {
        String currentDir = System.getProperty("user.dir");
        Path dataDirPath = Paths.get(currentDir, "data");
        Path path = Paths.get(currentDir, "data", this.fileName);
        createFileIfNotExist(dataDirPath, path);
        try (BufferedWriter bufferWriter = Files.newBufferedWriter(path)) {
            for (int i = 0; i < tasks.size(); i++) {
                bufferWriter.write(tasks.get(i).toMemoryFormat());
                bufferWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates directory and file if they do not exist.
     *
     * @param dataDirPath path to directory.
     * @param path path to file.
     */
    private static void createFileIfNotExist(Path dataDirPath, Path path) {
        if (!Files.exists(dataDirPath)) {
            try {
                Files.createDirectories(dataDirPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
