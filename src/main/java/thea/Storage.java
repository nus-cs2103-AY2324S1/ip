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
    private final String fileName;

    /**
     * Constructs a new Storage object.
     *
     * @param fileName name of file containing past data.
     */
    public Storage(String fileName) {
        this.fileName = fileName;
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
        if (!Files.exists(dataDirPath)) {
            return new ArrayList<>();
        }
        if (!Files.exists(path)) {
            return new ArrayList<>();
        }
        String line;
        ArrayList<Task> retrievedTasks = new ArrayList<>();
        Task task;
        try (BufferedReader bufferReader = Files.newBufferedReader(path)) {
            line = bufferReader.readLine();
            while (line != null) {
                String[] splitLine = line.split(" [|] ");
                if (splitLine[0].equals("T")) {
                    task = new ToDo(splitLine[2]);
                } else if (splitLine[0].equals("D")) {
                    task = new Deadline(splitLine[2], splitLine[3]);
                } else if (splitLine[0].equals("E")) {
                    task = new Event(splitLine[2], splitLine[3], splitLine[4]);
                } else {
                    throw new FileCorruptedException("Unexpected File Format Found. File might be corrupted.");
                }
                if (splitLine[1].equals("1")) {
                    task.markAsDone();
                }
                retrievedTasks.add(task);
                line = bufferReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return retrievedTasks;
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
        if (!Files.exists(dataDirPath)) {
            try {
                Files.createDirectories(dataDirPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            if (!Files.exists(path)) {
                try {
                    Files.createFile(path);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        try (BufferedWriter bufferWriter = Files.newBufferedWriter(path)) {
            for (int i = 0; i < tasks.size(); i++) {
                bufferWriter.write(tasks.get(i).toMemoryFormat());
                bufferWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
