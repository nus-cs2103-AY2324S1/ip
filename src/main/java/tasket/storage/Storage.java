package tasket.storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import tasket.data.TaskList;
import tasket.exception.TasketException;
import tasket.parser.Parser;
import tasket.task.Task;

/**
 * The class for storage.
 */
public class Storage {

    private final String filePath;
    private final File dataFile;

    /**
     * The constructor for storage.
     *
     * @param filePath The file path for saving the tasks.
     */
    public Storage(String filePath) {
        assert !filePath.isEmpty();
        this.filePath = filePath;
        dataFile = new File(filePath);
    }

    /**
     * Loads the task file and convert it to task list.
     * If the file is corrupted, remove the corrupted tasks.
     *
     * @return A list of saved tasks.
     * @throws TasketException If the file cannot be found.
     */
    public ArrayList<Task> load() throws TasketException {
        ArrayList<Task> taskList = new ArrayList<>();
        validateSaveFile();
        boolean isFileCorrupted = false;
        Scanner sc = null;
        try {
            sc = new Scanner(dataFile);
            while (sc.hasNext()) {
                try {
                    taskList.add(Parser.parseSaveString(sc.nextLine()));
                } catch (TasketException e) {
                    isFileCorrupted = true;
                }
            }

        } catch (FileNotFoundException e) {
            throw new TasketException("File not found");
        } finally {
            if (sc != null) {
                sc.close();
            }
        }

        if (isFileCorrupted) {
            rewriteSaveFile(new TaskList(taskList));
        }

        return taskList;
    }

    /**
     * Append a new task to the task list.
     * \
     * @param taskSave The task to be saved in save format.
     * @throws TasketException If IO error occurs.
     */
    public void append(String taskSave) throws TasketException {
        assert !taskSave.isEmpty();

        FileWriter fw = null;

        try {
            try {
                fw = new FileWriter(dataFile, true);
                fw.write(taskSave + System.lineSeparator());
            } catch (IOException e) {
                throw new TasketException("Error occurred when saving the task. The task will not be saved");
            } finally {
                if (fw != null) {
                    fw.close();
                }
            }
        } catch (IOException e) {
            throw new TasketException("Error occurred while saving the file");
        }
    }

    /**
     * Rewrites the save file.
     * This is done whenever there is mark, unmark and delete operations.
     *
     * @param taskList The task list to get the tasks from.
     * @throws TasketException If IO error occurs.
     */
    public void rewriteSaveFile(TaskList taskList) throws TasketException {
        assert taskList != null;

        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            try {
                fw = new FileWriter(dataFile, false);
                bw = new BufferedWriter(fw);

                for (int i = 0; i < taskList.size(); i++) {
                    bw.write(taskList.getTaskSaveString(i) + System.lineSeparator());
                }

            } catch (IOException e) {
                throw new TasketException("Error occurred while saving the file");
            } finally {
                if (fw != null && bw != null) {
                    bw.close();
                    fw.close();
                }
            }

        } catch (IOException e) {
            throw new TasketException("Error occurred while saving the file");
        }
    }

    /**
     * Create a save file if it does not exist.
     * The parent folders are also created.
     *
     * @throws TasketException If IO error occurs.
     */
    private void validateSaveFile() throws TasketException {
        try {
            Files.createDirectories(Path.of(filePath.substring(0, filePath.lastIndexOf("/"))));
            if (!dataFile.exists()) {
                Files.createFile(Path.of(filePath));
            }
        } catch (IOException e) {
            throw new TasketException("Unable to retrieve save file. This session will not be saved");
        }
    }
}
