package Duke;


import java.io.FileWriter;
import java.io.File;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;

/**
 * The Storage class represents the storage for the Duke program.
 * It provides methods to save and load tasks from a file so tasks are not lost when bot is closed.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a new Storage object with the given file path.
     * @param filePath the file path to save and load tasks from
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the given list of tasks to the file specified by the file path.
     * @param taskList an ArrayList of SingleTask objects representing the list of tasks to save
     * @throws IOException if there is an error writing to the file
     */
    public void saveTasks(ArrayList<SingleTask> taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (SingleTask task : taskList) {
            fw.write(task.toSaveString() + "\n");
        }
        fw.close();
    }

    /**
     * Loads a list of tasks from the file specified by the file path.
     * @return an ArrayList of SingleTask objects representing the list of tasks loaded from the file
     * @throws IOException if there is an error reading from the file
     * @throws DukeException if there is an error parsing the data in the file
     */
    public ArrayList<SingleTask> loadTasks() throws IOException, DukeException {
        ArrayList<SingleTask> taskList = new ArrayList<>();
        File f = new File(filePath);
        if (!f.exists()) {
            f.createNewFile();
            return taskList;
        }
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            String s = sc.nextLine();
            String[] parts = s.split("\\|");
            String type = parts[0];
            boolean isDone = Integer.parseInt(parts[1]) == 1;
            System.out.println();
            String description = parts[2];
            SingleTask task;
            switch (type) {
                case "T":
                    task = new ToDo(description);
                    break;
                case "D":
                    String by = parts[3];
                    task = new Deadline(description, by);
                    break;
                case "E":
                    String from = parts[3];
                    String to = parts[4];
                    task = new Event(description, from, to);
                    break;
                default:
                    throw new DukeException("Invalid task type in save file");
            }
            if (isDone) {
                task.isDone = true;
            }
            taskList.add(task);
        }
        return taskList;
    }
}