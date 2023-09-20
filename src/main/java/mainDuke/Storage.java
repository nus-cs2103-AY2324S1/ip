package mainDuke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import mainDuke.exceptions.DukeException;
import mainDuke.task.Deadline;
import mainDuke.task.Event;
import mainDuke.task.Task;
import mainDuke.task.Todo;

/**
 * Saves and loads tasks to hard drive in String form.
 */
class Storage {
    /**
     * Name of file and path which the data is to be stored.
     */
    static final String FILE_NAME = "./data/duke.txt";

    /**
     * Check if file that is to be written to and loaded from exists, if
     * it does not, create one.
     * @return true if file was found at first, else false.
     */
    static boolean fileExists() {
        Path path = Paths.get(FILE_NAME);
        try {
            if (!Files.exists(path)) {
                Ui.print("data file not found, creating a new one");
                Path dirPath = Paths.get("./data");
                Files.createDirectories(dirPath);
                File file = new File(FILE_NAME);
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Wipes file and save tasks given in param to hard drive.
     * @param tasks ArrayList of tasks to be saved.
     */
    public static void saveTask(ArrayList<Task> tasks) {
        fileExists();
        try (FileWriter fileWriter = new FileWriter(FILE_NAME)) {
            for (Task task : tasks) {
                String text = "";
                String desc = task.getTags() + " " + task.getDescription();
                if (task instanceof Todo) {
                    String done = task.getisDone()
                            ? "1"
                            : "0";
                    text = "T|" + done + "|" + desc;
                } else if (task instanceof Event) {
                    String done = task.getisDone()
                            ? "1"
                            : "0";
                    LocalDate from = ((Event) task).getFrom();
                    LocalDate to = ((Event) task).getTo();
                    text = "E|" + done + "|" + desc + "|" + from + "|" + to;
                } else if (task instanceof Deadline) {
                    String done = task.getisDone()
                            ? "1"
                            : "0";
                    LocalDate by = ((Deadline) task).getBy();
                    text = "D|" + done + "|" + desc + "|" + by;
                }
                fileWriter.write(text + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads task from hard drive and returns it.
     * @return ArrayList of tasks.
     * @throws DukeException if there are no tasks in the hard drive.
     */
    public static ArrayList<Task> loadTasks() throws DukeException {
        fileExists();
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(FILE_NAME));
            while (sc.hasNextLine()) {
                String next = sc.nextLine();
                Task nextTask = Task.parse(next);
                tasks.add(nextTask);
            }
            return tasks;
        } catch (IOException e) {
            throw new DukeException("There are no tasks");
        }
    }
}
