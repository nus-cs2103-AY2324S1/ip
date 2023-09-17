package duke.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Storage class is used to load and save the task list to a file.
 */
public class Storage {

    public static final String SEPARATOR = " !%&%! ";
    private final File folder;
    private final File file;
    private final String filePath;
    private final String folderPath;

    /**
     * Constructs a Storage object.
     *
     * @param folderPath The path of the folder to store the file.
     * @param fileName   The name of the file to store the task list.
     */
    public Storage(String folderPath, String fileName) {
        this.filePath = folderPath + "/" + fileName;
        this.folderPath = folderPath;
        this.file = new File(this.filePath);
        this.folder = new File(this.folderPath);
    }

    /**
     * Loads the task list from the file.
     * <p>If the file is not found or the format is wrong, it will throw an exception.</p>
     *
     * @return List of tasks.
     * @throws DukeException If the file is not found or the format is wrong.
     */
    public List<Task> load() throws DukeException {
        List<Task> taskList = new ArrayList<>();
        assert file.exists() : "File should exist";

        try {
            Scanner sc = new Scanner(file);
            loadToList(sc, taskList);
            sc.close();
        } catch (FileNotFoundException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException();
        }
        return taskList;
    }

    /**
     * Loads the file to the task list.
     *
     * @param sc       The scanner of the file.
     * @param taskList The list of tasks.
     * @throws DukeException If the format is wrong.
     */
    private void loadToList(Scanner sc, List<Task> taskList) throws DukeException {
        while (sc.hasNext()) {
            String[] commandData = sc.nextLine().split(SEPARATOR);
            Task task;
            switch (commandData[0]) {
            case "T":
                task = new Todo(commandData[2]);
                break;
            case "D":
                task = new Deadline(commandData[2], Time.parseDateTime(commandData[3]));
                break;
            case "E":
                task = new Event(commandData[2], Time.parseDateTime(commandData[3]),
                        Time.parseDateTime(commandData[4]));
                break;
            default:
                throw new DukeException();
            }
            if (taskList.stream().anyMatch(t -> t.equals(task))) {
                throw new DukeException(); // Found duplicate tasks
            }

            if (commandData[1].equals("1")) {
                task.mark(true);
            } else if (commandData[1].equals("0")) {
                task.mark(false);
            } else {
                throw new DukeException();
            }
            taskList.add(task);
        }
    }

    /**
     * Creates a new file to store the task list.
     * <p>If the file already exists, it will clear the file.<br>
     * If the folder does not exist, it will create the folder.<br>
     * If the file cannot be created, it will throw a runtime exception.</p>
     */
    public void createFile() {
        if (!this.folder.exists()) {
            folder.mkdirs();
        }
        assert this.folder.exists() : "Folder should be created";
        try {
            if (!file.exists()) {
                Files.createFile(Paths.get(this.filePath));
            } else {
                clearFile();
            }
            assert this.file.exists() : "File should be created";
        } catch (IOException e) {
            /* Here is reach if something terrible happened.
               It is best to throw a runtime exception. */
            throw new RuntimeException(e);
        }
    }

    /**
     * Clears the file.
     *
     * @throws IOException If the file is not found.
     */
    private void clearFile() throws IOException {
        assert file.exists() : "File should exist";

        FileWriter fw = new FileWriter(this.filePath);
        fw.write("");
        fw.close();
    }

    /**
     * Appends the text to the end of the file.
     *
     * @param text The text to be appended.
     * @throws DukeException If the file is not found.
     */
    public void appendFile(String text) throws DukeException {
        assert file.exists() : "File should exist";
        try {
            FileWriter fw = new FileWriter(this.filePath, true);
            fw.write(text);
            fw.write("\n");
            fw.close();
        } catch (IOException e) {
            throw new DukeException();
        }
    }

    /**
     * Saves the task list to the data file. It will clear the file and
     * write the task list to the file.
     *
     * @param fileFormattedTaskList The task list in file format.
     * @throws DukeException If the file is not found.
     */
    public void save(String[] fileFormattedTaskList) throws DukeException {
        assert file.exists() : "File should exist";

        try {
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(""); // Clear the file
            for (String fileFormatTask : fileFormattedTaskList) {
                fw.write(fileFormatTask);
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException();
        }
    }
}
