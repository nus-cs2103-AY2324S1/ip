package chatbot.storage;

import java.io.*;
import chatbot.task.*;

public class Storage {
    private final String FILEPATH;

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
