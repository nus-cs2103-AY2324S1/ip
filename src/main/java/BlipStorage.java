import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.time.LocalDateTime;


public class BlipStorage {

    private static String filePath;
    public BlipStorage(String filePath) {
        this.filePath = filePath;
    }

    public static TaskList loadFile() throws BlipException {
        TaskList tasks = new TaskList();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return tasks;
            }
            FileReader fileReader = new FileReader(file);
            BufferedReader finalReader = new BufferedReader(fileReader);
            String lineToRead;
            while ((lineToRead = finalReader.readLine()) != null) {
                Task task = Task.loadTaskFromFile(lineToRead);
                tasks.addTask(task);
            }
            finalReader.close();
        } catch (IOException e) {
            System.out.println("Error reading line: " + e.getMessage());
        } catch (DateTimeFormatException e) {
            System.out.println("Error with date time format: " + e.getMessage());
        }
        return tasks;
    }

    public static void saveToFile(TaskList tasks) {
        try {
            File file = new File(filePath);
            File fileDirectory = file.getParentFile();

            if (!fileDirectory.exists()) {
                fileDirectory.mkdirs();
            }
            FileWriter fileWriter = new FileWriter(file);

            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.getTask(i);
                fileWriter.write(task.saveToFileString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e2) {
            System.out.println("Error saving to file: " + e2.getMessage());
        }
    }
}
