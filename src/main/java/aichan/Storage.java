package aichan;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import aichan.task.Task;

/**
 * Represents a storage which deals with loading tasks and saving tasks.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a storage object.
     *
     * @param filePath File path for storing and loading tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private File openFile() throws IOException, AiChanException {
        File file = new File(filePath);
        if (file.exists()) {
            return file;
        } else {
            handleFileMissing();
            return new File(filePath);
        }
    }

    private void handleFileMissing() throws AiChanException {
        try {
            createDirectory("./data");
            createFile(filePath);
        } catch (IOException e) {
            throw new AiChanException("Error happens when creating a file.\n "
                    + "Pls create a file 'tasks.txt' inside a directory 'data'.");
        }
    }

    private void createDirectory(String directory) throws IOException {
        File dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    private void createFile(String filePath) throws IOException {
        File f = new File(filePath);
        f.createNewFile();
    }

    /**
     * Saves the tasks to the file.
     *
     * @param tasks TaskList contains current tasks.
     * @throws AiChanException If an error occurs when saving tasks.
     */
    public void saveTasks(TaskList tasks) throws AiChanException {
        try {
            File file = openFile();
            FileWriter filewriter = new FileWriter(file);

            for (Task task : tasks.getTasks()) {
                String line = task.toFileLine();
                if (line != null) {
                    filewriter.write(line + "\n");
                }
            }
            filewriter.close();
        } catch (IOException e) {
            throw new AiChanException("Error saving the tasks into the file.");
        }
    }

    /**
     * Loads tasks which are stored in the file.
     *
     * @return ArrayList which contains the tasks.
     * @throws AiChanException If an error occurs when loading tasks.
     */
    public ArrayList<Task> loadTasks() throws AiChanException {
        ArrayList<Task> arrTask = new ArrayList<>();

        try {
            File file = openFile();
            Scanner scn = new Scanner(file);

            while (scn.hasNextLine()) {
                String line = scn.nextLine();
                if (line.isEmpty()) {
                    break;
                }

                Task task = Task.getTaskFromFileLine(line);
                if (task != null) {
                    arrTask.add(task);
                }
            }

            scn.close();
        } catch (IOException e) {
            throw new AiChanException("Error loading tasks from file.");
        }
        return arrTask;
    }
}
