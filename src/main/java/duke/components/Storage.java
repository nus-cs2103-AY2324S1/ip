package duke.components;

import duke.tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
     * @return List of tasks
     */
    public List<Task> load() {
        List<Task> loadedTasks = new ArrayList<>();
        try {
            File fileToLoad = new File(getFilePath());
            Scanner listReader = new Scanner(fileToLoad);
            while (listReader.hasNextLine()) {
                try {
                    Task newTask = Parser.parseFileTasks(listReader.nextLine());
                    loadedTasks.add(newTask);
                } catch (IllegalArgumentException e) {
                    System.out.println("Load file is corrupted, skipping task");
                } catch (Parser.UnsupportedTaskType e) {
                    System.out.printf("Unsupported task type %s, skipping task\n", e.getTaskType());
                }
            }
            listReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file: " + getFilePath());
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
     * Writes task list to file
     * @param taskList TaskList object containing tasks to write to file
     * @throws IOException I/O problem encountered, unable to save file
     */
    public void save(TaskList taskList) throws IOException {
        try {
            createFileIfNotExists();
            try (FileWriter taskListWriter = new FileWriter(getFilePath())) {
                for (Task t : taskList.getTasks()) {
                    taskListWriter.write(t.generateSaveString() + "\n");
                }
            }
        } catch (IOException e) {
            throw new IOException("Unable to create file: " + fileName + "\n"
                    + "Reason: " + e.getMessage());
        }
    }

    /**
     * Writes a single task into the file, useful if adding only a single task
     * @param task Task to be written into the file
     * @throws IOException Thrown if task cannot be written into file
     */
    public void write(Task task) throws IOException {
        createFileIfNotExists();
        try (FileWriter taskListWriter = new FileWriter(getFilePath())){
            taskListWriter.append(String.format("%s\n", task.generateSaveString()));
        } catch (IOException e) {
            throw new IOException("Unable to save task list to destination: "  + getFilePath() + "\n"
                    + "Reason: " + e.getMessage());
        }
    }

    /**
     * Creates file if file is not found at filepath
     * @throws IOException Thrown if file cannot be created for some reason
     */
    protected void createFileIfNotExists() throws IOException {
        File saveDir = new File(fileDir);
        if (!saveDir.exists()) {
            saveDir.mkdirs();
            File saveFile = new File(getFilePath());
            saveFile.createNewFile();
        }
    }
}
