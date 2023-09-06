package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * FileManager that takes care of reading and writing takes to a file.
 */
public class Storage {
    private static String filePath = "./data/duke.txt";

    /**
     * Read the task from the file.
     */
    public static void readTask(TaskList tasks, Ui ui) {
        try {
            File directory = new File("./data");
            File file = new File(filePath);

            if (!directory.exists()) {
                directory.mkdir();
            }

            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    ui.showError("Error creating new file: " + e.getMessage());
                }
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                Task task;

                String line = scanner.nextLine();
                String[] parts = line.split("\\|");
                String taskType = parts[0];
                boolean isDone = parts[1].equals("true");
                String description = parts[2];

                switch (taskType) {
                case "T":
                    task = new Todo(description);
                    break;
                case "D":
                    String date = parts[3];
                    task = new Deadline(description, date);
                    break;
                case "E":
                    String from = parts[3];
                    String to = parts[4];
                    task = new Event(description, from, to);
                    break;
                default:
                    continue;
                }

                if (isDone) {
                    task.mark();
                }

                tasks.add(task);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            ui.showError(e.getMessage());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }


    /**
     * Write the task to the file.
     */
    public static void writeTask(TaskList tasks, Ui ui) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                fw.write(task.getRaw() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            ui.showError("An error occurred while writing to file.");
        }
    }
}
