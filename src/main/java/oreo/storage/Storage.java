package oreo.storage;

import oreo.exception.IllegalDateTimeException;
import oreo.task.Task;
import oreo.task.TaskList;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class implements the storage function of the chatbot
 *
 * @author Daniel Loh
 * @version 03/09/2023
 */
public class Storage {
    private final File storageFile;

    private String filePath;

    /**
     * Constructor for storage
     *
     * @param filePath file path to where file is saved
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.storageFile = new File(filePath);
        loadFile();
    }

    private void loadFile() {
        try {
            storageFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Reads saved file if it exists
     *
     * @param tasks
     * @throws FileNotFoundException
     * @throws IllegalDateTimeException
     */
    public void readFile(TaskList tasks) throws FileNotFoundException, IllegalDateTimeException {
        Scanner sc = new Scanner(this.storageFile);
        while (sc.hasNext()) {
            int id = sc.nextInt();
            int mark = sc.nextInt();
            String description = sc.nextLine();
            Task newTask = Task.generateSavedTask(id, mark == 1, description);
            tasks.add(newTask);
        }
    }

    /**
     * Clears file in the file path
     *
     */
    public void clearFile() {
        try {
            new FileWriter(filePath, false).close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Writes file with list of task input upon bye command
     *
     * @param tasks TaskList with all the task
     * @throws IOException for filewriter
     */
    public void writeFile(TaskList tasks) throws IOException {
        clearFile();
        FileWriter fw = new FileWriter(filePath, true);
        int numberOfTasks = tasks.getNumberOfTask();
        for (int i = 0; i < numberOfTasks; i++) {
            String data = tasks.get(i).writeToFile();
            fw.write(data);
        }
        fw.close();
    }
}
