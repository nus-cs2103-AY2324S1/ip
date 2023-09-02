package jarvis;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

import jarvis.exceptions.InvalidTaskFormatException;
import jarvis.tasks.Task;

public class Storage {
    private static final String FILE_PATH = "./jarvis/data/jarvis.txt";

    public void saveTasks(ArrayList<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                writer.write(task.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String nextLine;
            while ((nextLine = reader.readLine()) != null) {
                tasks.add(Parser.parseStringToTask(nextLine));
            }
        } catch (IOException e) {
            System.err.println("Unable to load tasks");
        } catch (InvalidTaskFormatException e) {
            System.err.println("Invalid Task Format when loading tasks");
        }
        return tasks;
    }
}
