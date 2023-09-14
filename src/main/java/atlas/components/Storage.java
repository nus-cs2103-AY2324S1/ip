package atlas.components;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import atlas.tasks.Task;

/**
 * Handler to load tasks from file and save tasks to file
 */
public class Storage {
    final String fileDir;
    final String fileName;

    /**
     * Constructs a storage object
     * @param fileDir Location of file to save to
     * @param fileName Name of file to save to
     */
    public Storage(String fileDir, String fileName) {
        this.fileDir = fileDir;
        this.fileName = fileName;
    }

    /**
     * Loads tasks from file
     * @return List of tasks loaded from the file specified by getFilePath()
     */
    public List<Task> load() {
        try {
            File fileToLoad = new File(getFilePath());
            Scanner listReader = new Scanner(fileToLoad);
            List<Task> loadedTasks = addTasksToList(listReader);
            listReader.close();
            return loadedTasks;
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file: " + getFilePath());
        }
        return new ArrayList<>();
    }

    /**
     * Creates list of tasks read from scanner
     * @param sc Scanner to read tasks from file
     * @return List of tasks parsed from scanner
     */
    private List<Task> addTasksToList(Scanner sc) {
        List<Task> loadedTasks = new ArrayList<>();
        while (sc.hasNextLine()) {
            try {
                Task newTask = Parser.parseFileTasks(sc.nextLine());
                loadedTasks.add(newTask);
            } catch (IllegalArgumentException e) {
                System.out.println("Task line is corrupted, skipping task");
            } catch (Parser.UnsupportedTaskType e) {
                System.out.println("Unsupported task type " + e.getTaskType() + ", skipping task");
            }
        }
        return loadedTasks;
    }

    /**
     * Returns relative file path of save file
     * @return Relative file path of save file
     */
    private String getFilePath() {
        return fileDir + fileName;
    }

    /**
     * Saves task list to file. The primary purpose of this function is to catch
     * exceptions (specifically IOExceptions) and add a wrapper to the error message.
     * @param taskList TaskList object containing tasks to write to file
     * @throws IOException I/O problem encountered, unable to save file
     */
    public void save(TaskList taskList) throws IOException {
        try {
            writeTasksToFile(taskList);
        } catch (IOException e) {
            throw new IOException("Unable to create file: " + fileName + "\n"
                    + "Reason: " + e.getMessage());
        }
    }

    /**
     * Writes tasks to file.
     * @param taskList TaskList whose tasks are to be written to the file
     * @throws IOException Thrown if save file does not exist and cannot be created,
     *     or if the file cannot be written to
     */
    private void writeTasksToFile(TaskList taskList) throws IOException {
        List<Task> tasksToWrite = taskList.getTasks();

        createFileIfNotExists();

        try (FileWriter taskListWriter = new FileWriter(getFilePath())) {
            for (Task t : tasksToWrite) {
                taskListWriter.write(t.generateSaveString() + "\n");
            }
        }
    }

    /**
     * Creates file if file is not found at filepath
     * @throws IOException Thrown if file cannot be created for some reason
     */
    protected void createFileIfNotExists() throws IOException {
        File saveDir = new File(fileDir);

        if (!saveDir.exists()) {
            try {
                boolean hasCreatedDirs = saveDir.mkdirs();
                if (hasCreatedDirs) {
                    System.out.println("Created parent directories");
                }

                File saveFile = new File(getFilePath());

                boolean hasCreatedFile = saveFile.createNewFile();
                if (hasCreatedFile) {
                    System.out.println("Created new save file");
                }
            } catch (SecurityException e) {
                throw new IOException("Unable to create file due to security manager");
            }
        }
    }
}
