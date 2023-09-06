package duke;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class handles the creation, writing, and loading from a txt file.
 */
public class Storage {

    /**
     * This method loads pre-existing tasks from the file by converting text to Task objects,
     * or creates the file if it does not yet exist.
     */
    public ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            File file = new File("duke.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                Task task;
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");

                if (parts[0].equals("T")) {
                    task = new Todo(parts[2]);
                } else if (parts[0].equals("D")) {
                    task = new Deadline(parts[2], parts[3]);
                } else {
                    task = new Event(parts[2], parts[3], parts[4]);
                }

                boolean done = parts[1].equals("1");
                if (done) {
                    task.markDone();
                }

                taskList.add(task);
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Loading went wrong: " + e.getMessage());
        }
        return taskList;
    }

    /**
     * This method updates the file by converting the Tasks into text.
     * @param taskList
     */
    public void updateTasksFile(ArrayList<Task> taskList) {
        try {
            FileWriter writer = new FileWriter("duke.txt");
            for (Task task : taskList) {
                String done = task.isDone() ? "1" : "0";
                if (task instanceof Todo) {
                    writer.write(task.getClass().getSimpleName().charAt(0)
                            + " | " + done + " | " + task.getDescription() + "\n");
                } else if (task instanceof Deadline) {
                    writer.write(task.getClass().getSimpleName().charAt(0)
                            + " | " + done + " | " + task.getDescription() + "|"
                            + ((Deadline) task).getBy() + "\n");
                } else {
                    writer.write(task.getClass().getSimpleName().charAt(0)
                            + " | " + done + " | " + task.getDescription() + "|"
                            + ((Event) task).getFromTo() + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Writing went wrong: " + e.getMessage());
        }
    }

}
