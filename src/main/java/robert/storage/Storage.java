package robert.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import robert.exception.RobertException;
import robert.task.Deadline;
import robert.task.Event;
import robert.task.Task;
import robert.task.TaskList;
import robert.task.ToDo;

/**
 * A class that is used to load stored tasks to and from the hard disk.
 *
 * @author Lee Zhan Peng
 */
public class Storage {

    /** The file used to store the data of list of tasks */
    private final File tasksFile;

    /**
     * Constructs Storage using a file path.
     *
     * @param filePath the file path that stores the data of list of tasks.
     */
    public Storage(String filePath) {
        Path tasksFilePath = Paths.get(System.getProperty("user.dir"), filePath);
        this.tasksFile = new File(tasksFilePath.toString());
    }

    /**
     * Loads the stored tasks from hard disk into Robert.
     *
     * @return an ArrayList of Task stored in the hard disk.
     * @throws RobertException if the stored tasks in hard disk is corrupted or wrongly formatted.
     */
    public ArrayList<Task> load() throws RobertException {
        if (!this.tasksFile.exists()) {
            this.tasksFile.getParentFile().mkdirs();
            try {
                this.tasksFile.createNewFile();
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        ArrayList<Task> downloadedTasks = new ArrayList<>();

        try (Scanner scanner = new Scanner(this.tasksFile)) {
            while (scanner.hasNext()) {
                String[] taskParameters = scanner.nextLine().split(" \\| ");

                switch (taskParameters[0]) {
                case "T":
                    ToDo newToDo = new ToDo(taskParameters[2], taskParameters[1].equals("1"));
                    downloadedTasks.add(newToDo);
                    break;

                case "E":
                    LocalDate fromDate = LocalDate.parse(taskParameters[3]);
                    LocalDate toDate = LocalDate.parse(taskParameters[4]);

                    Event newEvent = new Event(taskParameters[2],
                            fromDate, toDate, taskParameters[1].equals("1"));
                    downloadedTasks.add(newEvent);
                    break;

                case "D":
                    LocalDate byDate = LocalDate.parse(taskParameters[3]);

                    Deadline newDeadline = new Deadline(taskParameters[2],
                            byDate, taskParameters[1].equals("1"));
                    downloadedTasks.add(newDeadline);
                    break;

                default:
                    throw new RobertException("An unknown task type was identified "
                            + "when parsing previously stored tasks.");
                }
            }
        } catch (Exception e) {
            throw new RobertException("Unable to load data from storage.");
        }

        return downloadedTasks;
    }

    /**
     * Saves the tasks from Robert into the hard disk.
     *
     * @param tasks a TaskList that contains all tasks that are currently recorded by Robert.
     * @throws RobertException if there is an issue saving the tasks into the hard disk.
     */
    public void save(TaskList tasks) throws RobertException {
        try {
            FileWriter fw = new FileWriter(this.tasksFile.toString(), false);
            for (Task task : tasks) {
                String storedLine;
                String taskDone = task.getStatusIcon().equals("X") ? "1" : "0";

                if (task instanceof ToDo) {
                    storedLine = "T | "
                            + taskDone + " | "
                            + task.getDescription();

                } else if (task instanceof Event) {
                    storedLine = "E | "
                            + taskDone + " | "
                            + task.getDescription() + " | "
                            + ((Event) task).getFromDate() + " | "
                            + ((Event) task).getToDate();

                } else {
                    storedLine = "D | "
                            + taskDone + " | "
                            + task.getDescription() + " | "
                            + ((Deadline) task).getByDate();
                }

                fw.write(storedLine + "\n");
            }

            fw.close();

        } catch (IOException e) {
            throw new RobertException("There seems to be a problem saving the tasks to your hard disk.");
        }
    }
}
