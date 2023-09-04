package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Saves and loads tasks to hard drive in String form
 */
class Storage {
    /**
     * name of file and path which the data is to be stored
     */
    static final String FILE_NAME = "./data/duke.txt";

    /**
     * check if file that is to be written to and loaded from exists, if
     * it does not, create one
     */
    static void checkFileExists() {
        Path path = Paths.get(FILE_NAME);
        try {
            if (!Files.exists(path)) {
                Ui.print("data file not found, creating a new one");
                Path dirPath = Paths.get("./data");
                Files.createDirectories(dirPath);
                File file = new File(FILE_NAME);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * wipes file and save tasks given in param to hard drive
     * @param tasks ArrayList of tasks to be saved
     */
    public static void saveTask(ArrayList<Task> tasks) {
        checkFileExists();
        try (FileWriter fileWriter = new FileWriter(FILE_NAME)) {
            for (Task task : tasks) {
                String text = "";
                if (task instanceof Todo) {
                    String done = task.getisDone()
                            ? "1"
                            : "0";
                    String desc = task.getDescription();
                    text = "T|" + done + "|" + desc;
                } else if (task instanceof Event) {
                    String done = task.getisDone()
                            ? "1"
                            : "0";
                    String desc = task.getDescription();
                    LocalDate from = ((Event) task).getFrom();
                    LocalDate to = ((Event) task).getTo();
                    text = "E|" + done + "|" + desc + "|" + from + "|" + to;
                } else if (task instanceof Deadline) {
                    String done = task.getisDone()
                            ? "1"
                            : "0";
                    String desc = task.getDescription();
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
     * loads task from hard drive and returns it
     * @return ArrayList of tasks
     * @throws DukeException if there are no tasks in the hard drive
     */
    public static ArrayList<Task> loadTasks() throws DukeException {
        checkFileExists();
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
