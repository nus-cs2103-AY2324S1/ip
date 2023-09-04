package duke.io;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Loader {

    private static final String FILE_PATH = "./data/duke.txt";

    public static List<Task> loadFromFile() {
        File file = new File(FILE_PATH);
        List<Task> tasks = new ArrayList<>();

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String data = sc.nextLine();
                String[] parts = data.split("\\|");

                Task task = null;
                switch (parts[0].trim()) {
                    case "T":
                        task = new Todo(parts[2].trim());
                        if (parts[1].trim().equals("1")) task.markAsDone();
                        break;
                    case "D":
                        task = new Deadline(parts[2].trim(), parts[3].trim());
                        if (parts[1].trim().equals("1")) task.markAsDone();
                        break;
                    case "E":
                        String[] timings = parts[3].trim().split(" ", 3);
                        task = new Event(parts[2].trim(), timings[0] + " " + timings[1], timings[2]);
                        if (parts[1].trim().equals("1")) task.markAsDone();
                        break;
                }
                tasks.add(task);
            }
            sc.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return tasks;
    }
}

