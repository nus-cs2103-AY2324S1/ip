import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Scanner;

public class Storage {
    private final File tasksFile;

    public Storage(String filePath) {
        Path tasksFilePath = Paths.get(System.getProperty("user.dir"), filePath);
        this.tasksFile = new File(tasksFilePath.toString());
    }

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
                    throw new RobertException("An unknown task type was identified when parsing previously stored tasks.");
                }
            }
        } catch (Exception e) {
            throw new RobertException("Unable to load data from storage.");
        }

        return downloadedTasks;
    }

    public void save(TaskList tasks) throws RobertException {
        try {
            FileWriter fw = new FileWriter(this.tasksFile.toString(), false);
            for (Task task : tasks) {
                String storedLine;
                String taskDone = task.isDone ? "1" : "0";

                if (task instanceof ToDo) {
                    storedLine = "T | "
                            + taskDone + " | "
                            + task.description;

                } else if (task instanceof Event) {
                    storedLine = "E | "
                            + taskDone + " | "
                            + task.description + " | "
                            + ((Event) task).fromDate + " | "
                            + ((Event) task).toDate;

                } else {
                    storedLine = "D | "
                            + taskDone + " | "
                            + task.description + " | "
                            + ((Deadline) task).byDate;
                }

                fw.write(storedLine + "\n");
            }

            fw.close();

        } catch (IOException e) {
            throw new RobertException("There seems to be a problem saving the tasks to your hard disk.");
        }
    }
}
