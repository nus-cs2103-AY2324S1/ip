package nobita.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import nobita.exception.NobitaException;
import nobita.task.TaskList;
import nobita.task.Task;
import nobita.task.ToDo;
import nobita.task.Deadline;
import nobita.task.Event;

/**
 * Class that encapsulates Storage.
 * Storage that is used to interact with local data files.
 *
 * @author Zheng Chenglong
 */
public class Storage {

    /** The file name that stores data */
    private final String fileName = "Nobita.txt";

    /** The directory path that the file locates */
    private final String directoryPath;

    /**
     * Constructs Storage with a file path.
     *
     * @param directoryPath The directory path that the data file is store in.
     */
    public Storage(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    /**
     * Reads the stored data file to Nobita.
     *
     * @return A TaskList that contains all stored tasks.
     */
    public TaskList readFile() {
        checkFileExist();
        File file = new File(getFilePath());
        TaskList tasks = new TaskList();
        try {
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()) {
                String[] data = sc.nextLine().split(";");
                Task toAdd = new Task("Test Subject");
                switch (data[0]) {
                    case "T":
                        toAdd = new ToDo(data[2]);
                        break;
                    case "D":
                        toAdd = new Deadline(data[2], data[3]);
                        break;
                    case "E":
                        toAdd = new Event(data[2], data[3], data[4]);
                        break;
                    default:
                }

                if (data[1].equals("1")) {
                    toAdd.markComplete();
                }
                tasks.addTask(toAdd);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return tasks;
    }

    /**
     * Write the tasks from Nobita into the file located in the directory specified.
     *
     * @throws NobitaException If error occur when saving file.
     */
    public void writeFile(TaskList tasks) throws NobitaException {
        try {
            FileWriter file = new FileWriter(getFilePath());
            for (Task task: tasks.getAllTasks()) {
                file.write(task.toFileFormat());
                file.write("\n");
            }
            file.close();
        } catch (IOException e) {
            throw new NobitaException(e.toString());
        }
    }

    /**
     * Return the file path.
     * Creates directory to the file if directory does not exist.
     *
     * @return String that represents the file path.
     */
    private String getFilePath() {
        Path pathDirectory = java.nio.file.Paths.get(directoryPath,"src", "main", "data");
        try {
            Files.createDirectories(pathDirectory);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        String filePath = pathDirectory.toString() + "\\" + this.fileName;
        return filePath;
    }

    /**
     * Check if file exist, do nothing if exist else create a new file.
     */
    private void checkFileExist() {
        File file = new File(getFilePath());
        try {
            file.createNewFile(); // create new file if file does not exist
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
