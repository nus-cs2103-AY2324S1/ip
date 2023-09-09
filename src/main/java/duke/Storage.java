package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with saving tasks into file and loading tasks from file.
 *
 * @author Qin Yan Er
 */
public class Storage {

    private String filePath;

    /**
     * Create a new Storage instance.
     *
     * @param filePath The file path where the list of tasks are stored in.
     */
    public Storage(String filePath) {
        assert filePath != null && !filePath.isEmpty() : "File path cannot be null or empty.";

        this.filePath = filePath;
    }

    /**
     * Saves the list of tasks into file.
     *
     * @param list Contains the list of tasks.
     */
    public void save(TaskList list) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (int i = 0; i < list.getSize(); i++) {
                fileWriter.write(list.getTask(i).saveTask() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Loads the list of tasks from file.
     *
     * @return an ArrayList instance containing the lists of tasks stored in the file.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } else {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String saveFormat = scanner.nextLine();
                    tasks.add(Task.loadData(saveFormat));
                }
                scanner.close();
            }
        } catch (IOException e) {
            System.out.println((e.getMessage()));
        }
        return tasks;
    }
}
