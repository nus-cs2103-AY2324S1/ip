package main.java.storage;

import main.java.Deadline;
import main.java.Event;
import main.java.Task;
import main.java.Todo;
import main.java.tasklist.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {
    private final Path directory;
    private File file;

    public Storage(String filePath) {
        this.directory = Paths.get(System.getProperty("user.dir"), "./ip/src/main/java/storage");
        if (!Files.exists(directory)) {
            try {
                Files.createDirectories(directory);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        this.file = new File(this.directory.toFile(), filePath);
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void saveTaskList(TaskList taskList) {
        try (FileWriter fileWriter = new FileWriter(this.file)) {
            for (int i = 0; i < taskList.numOfTasks(); i++) {
                Task taskToSave = taskList.getTask(i);
                String toSave = taskToSave.getSaveDescription();
                fileWriter.write(toSave);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadTaskList(TaskList taskList) {
        try (Scanner sc = new Scanner(this.file)) {
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                String[] task = input.split(" \\| ", 5);
                Boolean taskBool = task[1] == "1";
                String description = task[2];
                switch (task[0]) {
                case ("T"):
                    taskList.addTask(new Todo(description, taskBool));
                    break;
                case ("D"):
                    taskList.addTask(new Deadline(description, task[3], taskBool));
                    break;
                case ("E"):
                    taskList.addTask(new Event(description, task[3], task[4], taskBool));
                    break;
                default:
                    System.out.println("unknown file format");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}


