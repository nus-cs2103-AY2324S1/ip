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
 * Storage class is used to load and save the task list and settings to a file.
 */
public class Storage {

    public static final String SEPARATOR = " !%&%! ";
    private final File folder;
    private final String folderPath;
    private final File defaultFile;
    private File file;
    private String filePath;

    /**
     * Constructs a Storage object.
     *
     * @param folderPath The path of the folder to store the file.
     * @param fileName   The name of the file to store the task list.
     */
    public Storage(String folderPath, String fileName) {
        this.folderPath = folderPath;
        this.filePath = folderPath + "/" + fileName;
        this.defaultFile = new File(this.filePath);
        this.file = this.defaultFile;
        this.folder = new File(folderPath);
    }

    /**
     * Loads the task list from the file.
     * <p>If the file is not found or the format is wrong, it will throw an exception.</p>
     *
     * @param isLoadDefault Whether to load the default file.
     * @param alterFileName The name of alternative file to load.
     * @return The list of tasks.
     */
    public List<Task> loadTasks(boolean isLoadDefault, String alterFileName) throws DukeException {
        File file = isLoadDefault
                ? this.defaultFile
                : new File(this.folderPath + "/" + alterFileName);
        if (!file.exists()) {
            createFile(folder, file, file.getPath());
            throw new DukeException(String.format("OOPS!!! The %s file is not found.\n"
                    + "A new file is created.", isLoadDefault ? "default" : alterFileName));
        }

        List<Task> taskList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            loadToList(sc, taskList);
            sc.close();
        } catch (FileNotFoundException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException();
        }
        this.file = file;
        this.filePath = file.getPath();
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
     * Creates a new file given the folder, file, and file path as string.
     * <p>If the file already exists, it will clear the file.<br>
     * If the folder does not exist, it will create the folder.<br>
     * If the file cannot be created, it will throw a runtime exception.</p>
     *
     * @param folder   The folder to store the file.
     * @param file     The file to be created.
     * @param filePath The path of the file.
     */
    private void createFile(File folder, File file, String filePath) {
        if (!folder.exists() && !folder.mkdirs()) {
            throw new RuntimeException("Folder cannot be created");
        }
        assert folder.exists() : "Folder should be created";

        try {
            if (!file.exists()) {
                Files.createFile(Paths.get(filePath));
            } else {
                clearFile(filePath);
            }
            assert file.exists() : "File should be created";
        } catch (IOException e) {
            /* Here is reach if something terrible happened.
               It is best to throw a runtime exception. */
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates a new file to store the task list.
     */
    public void createTaskFile() {
        createFile(folder, file, filePath);
    }

    /**
     * Clears the file.
     *
     * @throws IOException If the file is not found.
     */
    private void clearFile(String filePath) throws IOException {
        assert file.exists() : "File should exist";

        FileWriter fw = new FileWriter(filePath);
        fw.write("");
        fw.close();
    }

    /**
     * Saves the task list to the data file. It will clear the file and
     * write the task list to the file.
     *
     * @param fileFormattedTaskList The task list in file format.
     * @throws DukeException If the file is not found.
     */
    public void saveTasks(String[] fileFormattedTaskList) throws DukeException {
        if (!file.exists()) {
            createFile(folder, file, filePath);
        }
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

    /**
     * Loads the previously saved alias file.
     * <p>If the file is not found or corrupted, it will create a new file and
     * load the default alias.</p>
     *
     * @throws DukeException If the file is not found or corrupted.
     */
    public void loadAlias() throws DukeException {
        checkAndCreateConfigFolder();

        File aliasFile = new File("./config/alias.txt");
        if (!aliasFile.exists()) {
            createFile(folder, aliasFile, aliasFile.getPath());
            Alias.initAlias();
            saveAlias(Alias.saveAliasFormat());
            throw new DukeException("OOPS!!! The alias file is not found.\n\n"
                    + "Default alias is loaded!");
        }

        try {
            Scanner sc = new Scanner(aliasFile);
            loadToAlias(sc, aliasFile);
            sc.close();
        } catch (IOException e) {
            throw new DukeException();
        }
    }

    /**
     * Loads the file to the alias list.
     *
     * @param sc             The scanner of the file.
     * @param aliasFile      The alias file that contains all the aliases
     * @throws IOException   If the file is not found.
     * @throws DukeException If the format is wrong.
     */
    private void loadToAlias(Scanner sc, File aliasFile) throws IOException, DukeException {
        while (sc.hasNext()) {
            String[] split = sc.nextLine().split(" -> ");
            if (split.length != 2) {
                Alias.initAlias();
                clearFile(aliasFile.getPath());
                saveAlias(Alias.saveAliasFormat());
                throw new DukeException("OOPS!!! The alias file is corrupted.\n\n"
                        + "Default alias is loaded!");
            }
            Alias.addAlias(split[0], split[1]);
        }
    }

    /**
     * Saves the alias list to the alias file. It will clear the file and
     * write the alias list to the file.
     *
     * @param aliasList      The alias list.
     * @throws DukeException If the file is not found.
     */
    public void saveAlias(List<String> aliasList) throws DukeException {
        checkAndCreateConfigFolder();

        try {
            FileWriter fw = new FileWriter("./config/alias.txt");
            fw.write(""); // Clear the file
            for (String alias : aliasList) {
                fw.write(alias);
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException();
        }
    }

    /**
     * Checks if the config folder exists. If not, it will create the folder.
     *
     * @throws DukeException If the folder cannot be created.
     */
    private static void checkAndCreateConfigFolder() throws DukeException {
        File configFolder = new File("./config");
        if (!configFolder.exists() && !configFolder.mkdirs()) {
            throw new DukeException("OOPS!!! The config folder cannot be created.\n\n"
                    + "Please try again later.");
        }
    }
}
