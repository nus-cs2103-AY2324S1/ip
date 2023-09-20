package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.data.task.Task;
import duke.parser.Parser;

/**
 * The Storage class read .txt file and load the data into TaskList
 * and write tasks in TaskList into .txt file.
 */
public class Storage {
    /** the path of the .txt file */
    private static String storageFilePath;
    private final Path path;

    /**
     * Constructor to initialize Storage.
     *
     * @param filePath the path of the .txt file.
     * @throws DukeException when the file does not end in .txt format.
     */
    public Storage(String filePath) throws DukeException {
        storageFilePath = filePath;
        path = Paths.get(filePath);
        assert isValidPath(path) : "Storage file should end with '.txt'";
    }

    private static boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }

    /**
     * Saves a task object into .txt file by converting it into string
     *
     * @param task the task object to be saved into .txt file.
     * @throws IOException when the path is invalid.
     */
    public static void save(Task task) throws IOException {
        FileWriter fw = new FileWriter(storageFilePath, true);
        fw.write(task.toWrite());
        fw.close();
    }

    /**
     * Saves an arraylist of tasks into .txt file by converting it into string
     *
     * @param tasks the arraylist of tasks to be saved into .txt file.
     * @throws IOException when the path is invalid.
     */
    public static void save(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(storageFilePath);
        for (Task task: tasks.getList()) {
            fw.write(task.toWrite());
        }
        fw.close();
    }

    /**
     * Loads all the tasks in the .txt file and store them into an arraylist.
     *
     * @return an arraylist of all the tasks in the .txt file.
     * @throws FileNotFoundException when the file has not been created.
     * @throws ParseException when the file contains invalid format.
     * @throws DukeException when the file is corrupted.
     */
    public ArrayList<Task> load() throws FileNotFoundException, ParseException, DukeException {
        File file = new File(storageFilePath);
        Scanner scanner = new Scanner(file);
        ArrayList<Task> taskList = new ArrayList<>();
        while (scanner.hasNext()) {
            Task task = Parser.parseTask(scanner.nextLine());
            taskList.add(task);
        }
        return taskList;
    }
}
