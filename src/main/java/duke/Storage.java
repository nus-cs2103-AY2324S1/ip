package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public static void loadTasksFromFile(ArrayList<Task> taskList) {
        try {
            File file = new File("duke.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                boolean isDone = parts[1].equals("1");
                String description = parts[2];
                Task task = new Task(description);
                if (isDone) {
                    task.markDone();
                }
                taskList.add(task);
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Loading went wrong: " + e.getMessage());
        }
    }

    public static void updateTasksFile(ArrayList<Task> taskList) {
        try {
            FileWriter writer = new FileWriter("duke.txt");
            for (Task task : taskList) {
                String isDone = task.isDone ? "1" : "0";
                if (task instanceof Todo) {
                    writer.write(task.getClass().getSimpleName().charAt(0)
                            + " | " + isDone + " | " + task.getDescription() + "\n");
                } else if (task instanceof Deadline) {
                    writer.write(task.getClass().getSimpleName().charAt(0)
                            + " | " + isDone + " | " + task.getDescription() + "|"
                            + ((Deadline) task).getBy() + "\n");
                } else {
                    writer.write(task.getClass().getSimpleName().charAt(0)
                            + " | " + isDone + " | " + task.getDescription() + "|"
                            + ((Event) task).getFromTo() + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Writing went wrong: " + e.getMessage());
        }
    }

}
