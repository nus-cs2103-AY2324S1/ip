package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.exception.KoraException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;


/**
 * Storage class is to load and save the tasks in the hard disk automatically.
 */
public class Storage {
    private static final String DEFAULT_PATH = "./data/savedtask.txt";

    private String path;

    /**
     * Class constructor of Storage.
     * @param filePath Location of the data stored.
     */
    public Storage(String filePath) {
        path = filePath;
    }

    /**
     * Creates folder and file if they don't exist.
     * @throws KoraException When unable to create the folder and file.
     */
    public void createFile() throws KoraException {
        File f = new File("./data");
        if (!f.exists()) {
            f.mkdir();
        }

        try {
            File ff = new File(path);
            if (!ff.exists()) {
                ff.createNewFile();
            }
        } catch (IOException e) {
            throw new KoraException("Unable to create file!");

        }
    }

    /**
     * Loads tasks from data stored.
     * @param taskList List of tasks that is stored.
     * @throws KoraException When unable to scan.
     */
    public void loadTask(TaskList taskList) throws KoraException {
        createFile();
        File f = new File(path);
        try {
            Scanner s = new Scanner(f);
            String[] array;
            while (s.hasNextLine()) {
                array = s.nextLine().split("/ ");
                taskList.addNoSaveTask(checkTask(array));
            }
        } catch (IOException e) {
            throw new KoraException("Unable to scan!");
        }
    }


    /**
     * Saves tasks in the current tasklist.
     * @param taskList Current list of tasks.
     * @throws KoraException When unable to add.
     */
    public void saveTask(TaskList taskList) throws KoraException {

        try (FileWriter fw = new FileWriter(path, false)) {
            fw.write(taskList.saveFormat());
        } catch (IOException e) {
            throw new KoraException("Couldn't add!");
        }
    }

    /**
     * Creates task according to different type and with data from array with strings from user input.
     * @param array String array from user input.
     * @return Task
     * @throws KoraException When the type of task is not valid.
     */
    public Task checkTask(String[] array) throws KoraException {
        assert array.length >= 2 : "The command is too short.";
        Task currentTask;
        if (array[0].contains("E")) {
            currentTask = new Event(array[2], array[3], array[4]);
        } else if (array[0].contains("D")) {
            currentTask = new Deadline(array[2], array[3]);
        } else if (array[0].contains("T")) {
            currentTask = new ToDo(array[2]);
        } else {
            throw new KoraException("Task not valid!");
        }

        if (array[1].equals("[X]")) {
            currentTask.setMarked();
        } else {
            currentTask.setUnmarked();
        }

        return currentTask;
    }
}
