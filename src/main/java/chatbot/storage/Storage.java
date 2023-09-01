package chatbot.storage;

import java.io.*;

import chatbot.task.*;

/**
 * The Storage class manages reading from and writing to a file for task data storage.
 */
public class Storage {
    private final String FILEPATH;

    /**
     * Constructor for this class
     *
     * @param FILEPATH filepath of the file
     */
    public Storage(String FILEPATH) {
        this.FILEPATH = FILEPATH;

        File directory = new File("./data");
        if (!directory.exists()) {
            directory.mkdir();
        }

        File file = new File(FILEPATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error initializing file: " + e.getMessage());
            }
        }
    }

    /**
     * Saves the tasks from the TaskList to the file.
     *
     * @param tasks taskList containing tasks to be saved
     */
    public void saveToFile(TaskList tasks) {
        try {
            FileWriter fileWriter = new FileWriter(FILEPATH);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Task task : tasks.getAllTasks()) {
                bufferedWriter.write(task.toFileFormat());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the file and returns them as a TaskList.
     *
     * @return TaskList containing tasks loaded from the file
     */
    public TaskList loadFromFile() {
        TaskList tasks = new TaskList();
        try {
            FileReader fileReader = new FileReader(FILEPATH);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Task task = Task.fromFileFormat(line);
                if (task != null) {
                    tasks.add(task);
                } else {
                    // Handle corrupted line here for stretch goal
                    System.out.println("Warning: Corrupted line skipped in file");
                }
            }

            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
        return tasks;
    }
}
