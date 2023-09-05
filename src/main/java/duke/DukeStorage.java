package duke;

import duke.exceptions.InsufficientArgumentsException;
import duke.exceptions.StorageCreationException;
import duke.tasks.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Represents the interface between the application and the storage file.
 * Allows the application to read and write to a storage txt file.
 */
public class DukeStorage implements Storage {
    private static final String DIRECTORY_PATH = "data";
    private static final String FILE_PATH = "duke.txt";

    public DukeStorage() throws IOException, StorageCreationException {
        this.initialiseStorage();
    }

    /**
     * Reads the tasks from the storage file.
     * If the storage file and directory does not exist, it will be created.
     *
     * @throws InsufficientArgumentsException If there are not enough arguments to create tasks.
     * @throws DateTimeParseException         If the date in storage is formatted wrongly.
     * @throws StorageCreationException       If the storage file and directory creation failed.
     * @throws IOException                    If the storage file creation failed.
     */
    @Override
    public List<Task> readStorage() throws InsufficientArgumentsException, DateTimeParseException,
            StorageCreationException, IOException {
        initialiseStorage();
        List<Task> tasks = new ArrayList<>();
        String storagePath = String.format("./%s/%s", DIRECTORY_PATH, FILE_PATH);
        File file = new File(storagePath);
        Scanner scanner = new Scanner(file);
        while (Objects.requireNonNull(scanner).hasNext()) {
            String input = scanner.nextLine();
            String taskCode = input.substring(0, 1);
            String taskInput = input.length() > 4 ? input.substring(4) : "";
            Task task = Parser.parse(taskCode, taskInput);
            tasks.add(task);
        }
        return tasks;
    }

    /**
     * Rewrites the storage file from a list of tasks.
     *
     * @param tasks The list of tasks to be written into storage.
     * @throws IOException If writing to the file failed.
     */
    @Override
    public void updateStorage(List<Task> tasks) throws IOException {
        String storagePath = String.format("./%s/%s", DIRECTORY_PATH, FILE_PATH);
        try {
            FileWriter fw = new FileWriter(storagePath);
            for (Task task : tasks) {
                fw.write(task.encode() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new IOException(Messages.FAILED_TO_WRITE_FILE_ERROR_MESSAGE);
        }
    }


    /**
     * Initialises the storage directory and file if they do not exist.
     *
     * @throws StorageCreationException       If the storage file and directory creation failed.
     * @throws IOException                    If the storage file creation failed.
     */
    private void initialiseStorage() throws IOException, StorageCreationException {
        String directoryPath = String.format("./%s", DIRECTORY_PATH);
        String storagePath = String.format("./%s/%s", DIRECTORY_PATH, FILE_PATH);
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            if (!directory.mkdir()) {
                throw new StorageCreationException(Messages.FAILED_TO_CREATE_FOLDER_ERROR_MESSAGE);
            }
        }
        File file = new File(storagePath);
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    throw new StorageCreationException(Messages.FAILED_TO_CREATE_FILE_ERROR_MESSAGE);
                }
            } catch (IOException e) {
                throw new IOException(Messages.FAILED_TO_CREATE_FILE_ERROR_MESSAGE);
            }
        }
    }
}
