package roo;

import roo.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File file;
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        file = new File(this.filePath);
    }

    public void initialise(TaskList tasks) {
        try {
            this.file = new File(this.filePath);
            if (!file.createNewFile()) {
                this.readFileContents(tasks);
            }
        } catch (IOException e) {
            System.err.println("I/O error greet: " + e.getMessage());
        }
    }

    public void readFileContents(TaskList tasks) throws FileNotFoundException {
        Scanner sc = new Scanner(this.file);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (!input.isEmpty()) {
                tasks.add(Parse.makeTask(input));
            }
        }
        sc.close();
    }

    public void clear() {
        try {
            Files.delete(Paths.get(filePath));
            this.file = new File(this.filePath);
        } catch (IOException err) {
            System.err.println("Error clearing " + err.getMessage());
        }
    }

    public void modifyFile(ArrayList<Task> tasks) {
        try {
            Files.delete(Paths.get(filePath));
            this.file = new File(this.filePath);
            FileWriter writer = new FileWriter(filePath, true);
            for (Task t : tasks) {
                writer.write(t.toString()  + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("I/O error modify " + e.getMessage());
        }
    }

    public void add(Task task) {
        try {
            FileWriter writer = new FileWriter(filePath, true);
            writer.write(task.toString() + "\n");
            writer.close();
        } catch (IOException e) {
            System.err.println("I/O error add " + e.getMessage());
        }

    }
}
