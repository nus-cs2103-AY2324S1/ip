package duke;

import duke.task.Task;
import duke.task.TaskList;
import duke.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The Storage class manages the reading and writing of task data to and from a file for the Duke application.
 */
public class Storage {
    private String filePath;
    private File file;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path where task data will be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
        makeDataDir();
        this.createFile();
    }

    /**
     * Creates the data directory if it does not exist.
     */
    private static void makeDataDir() {
        File dataDirectory = new File("./data/");
        if (!dataDirectory.exists()) {
            dataDirectory.mkdirs();
        }
    }

    /**
     * Creates the data file if it does not exist and returns whether it was successfully created.
     *
     * @return True if the file was created successfully, false otherwise.
     */
    private boolean createFile()  {
        try {
            return file.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Loads task data from the file and populates the provided TaskList.
     *
     * @param tasks The TaskList to populate with loaded task data.
     * @return The TaskList containing loaded tasks.
     * @throws DukeException If there is an issue while loading task data.
     */
    public TaskList loadIntoList(TaskList tasks) throws DukeException {
        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                tasks.add(Parser.dataToTask(line));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Your Chatbot will start from clean slate.");
        }
        return tasks;
    }

    /**
     * Writes the tasks from the TaskList to the data file.
     *
     * @param tasks The TaskList to write to the file.
     */
    public void writeListToFile(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(file);
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.get(i).toData());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Appends a single task to the data file.
     *
     * @param task The Task to append to the file.
     * @throws IOException If there is an issue while appending to the file.
     */
    public void appendToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(task.toData());
        fw.write("\n");
        fw.close();
    }
}
