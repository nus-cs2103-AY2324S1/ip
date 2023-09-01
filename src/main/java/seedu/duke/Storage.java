package seedu.duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a storage manager.
 * A storage manager is responsible for
 * reading and writing data into a database
 * such as .txt files.
 *
 * @author KAM JIA YUE
 * @since 2023-08-29
 */
public class Storage {
    private String filePath;

    /**
     * The main constructor of the Storage class.
     */
    public Storage() {}

    /**
     * Sets the path of the storage file.
     *
     * @param filePath Path of the storage file.
     */
    public void setHardDiskFilePath(String filePath) {
        this.filePath = filePath;
    };

    /**
     * Loads data from the storage file to the given task list.
     *
     * @param list Task list to load data to.
     */
    public void loadData(List<Task> list) {
        String directoriesPathString = this.getParentDirectoriesPathFromFilePath();
        Path directoriesPath = Paths.get(directoriesPathString);

        try {
            Files.createDirectories(directoriesPath);
            File data = new File(this.filePath);
            boolean isNewFile = data.createNewFile();

            if (isNewFile) {
                return;
            }

            Scanner fileScanner = new Scanner(data);
            while (fileScanner.hasNextLine()) {
                String input = fileScanner.nextLine();
                char taskType = input.charAt(0);


                switch (taskType) {
                case 'T':
                    this.loadTodo(list, input);
                    break;
                case 'D':
                    this.loadDeadline(list, input);
                    break;
                case 'E':
                    this.loadEvent(list, input);
                    break;
                default:
                    throw new DukeException(this.filePath + " file is corrupted");
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads a Todo task from the storage file to
     * the given task list.
     *
     * @param list Task list to load data to.
     * @param input Input from the user.
     * @throws DukeException If there are any file corruptions.
     */
    public void loadTodo(List<Task> list, String input) throws DukeException {
        int[] vLines = newVLines(2, input);

        Task task = new Todo(input.substring(vLines[1] + 2));
        if (input.charAt(4) == '1') {
            task.setDone();
        }
        list.add(task);
    }

    /**
     * Loads a Deadline task from the storage file to
     * the given task list.
     *
     * @param list Task list to load data to.
     * @param input Input from the user.
     * @throws DukeException If there are any file corruptions.
     */
    public void loadDeadline(List<Task> list, String input) throws DukeException {
        int[] vLines = newVLines(3, input);

        String description = input.substring(vLines[1] + 2, vLines[2] - 1);
        String by = input.substring(vLines[2] + 2);

        Task task = new Deadline(description, by);
        if (input.charAt(4) == '1') {
            task.setDone();
        }
        list.add(task);
    }

    /**
     * Loads an Event task from the storage file to
     * the given task list.
     *
     * @param list Task list to load data to.
     * @param input Input from the user.
     * @throws DukeException If there are any file corruptions.
     */
    public void loadEvent(List<Task> list, String input) throws DukeException {
        int[] vLines = newVLines(4, input);

        String description = input.substring(vLines[1] + 2, vLines[2] - 1);
        String from = input.substring(vLines[2] + 2, vLines[3] - 1);
        String to = input.substring(vLines[3] + 2);

        Task task = new Event(description, from, to);
        if (input.charAt(4) == '1') {
            task.setDone();
        }
        list.add(task);
    }

    /**
     * Creates an array indicating the indexes of
     * the vertical lines of the user inputs stored
     * in the storage file.
     *
     * @param size Size of the array to be created.
     * @param input Input from the user.
     * @return An array of a specific size.
     * @throws DukeException If there are any file corruptions.
     */
    public int[] newVLines(int size, String input) throws DukeException {
        int[] vLines = new int[size];
        for (int i = 0, j = 0; i < input.length(); i++) {
            if (input.charAt(i) != '|') {
                continue;
            }
            if (input.charAt(i - 1) != ' ' || input.charAt(i + 1) != ' ') {
                throw new DukeException(this.filePath + " file is corrupted");
            }
            vLines[j++] = i;
        }
        return vLines;
    }

    /**
     * Gets all the parent directories from the
     * storage file path.
     *
     * @return all the parent directories from the storage file path.
     */
    public String getParentDirectoriesPathFromFilePath() {
        String path = "";
        for (int i = this.filePath.length() - 1; i >= 0; i--) {
            if (!this.filePath.substring(i, i + 1).equals("/")) {
                continue;
            }
            path = this.filePath.substring(0, i + 1);
            break;
        }
        return path;
    }

    /**
     * Updates the storage file with the latest
     * information in the given task list.
     *
     * @param list Task list as reference to write data to the storage file.
     */
    public void updateHardDisk(List<Task> list) {
        File oldFile = new File(this.filePath);
        oldFile.delete();
        File newFile = new File(this.filePath);

        try {
            FileWriter fileWriter = new FileWriter(newFile);
            for (Task task : list) {
                fileWriter.write(task.toStringForSave() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
