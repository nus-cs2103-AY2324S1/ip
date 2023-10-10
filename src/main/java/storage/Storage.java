package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Scanner;
import java.nio.file.Path;

import parser.Parser;
import tasks.Deadlines;
import tasks.Events;
import tasks.Task;
import tasks.TaskList;
import tasks.ToDos;


/**
 * The `Storage` class is responsible for managing the persistence of tasks by
 * reading and writing them to an external file named "OUTPUT.txt". It provides
 * methods to load tasks from the file, write tasks to the file, and overwrite
 * the file with updated task information.
 */
public class Storage {
    private FileWriter pw;
    private final String currdir;
    private final TaskList tasks;
    private FileWriter archive;

    /**
     * Constructor for the Storage class.
     *
     * @param dir   The directory where the data is stored.
     * @param tasks The TaskList instance for managing tasks.
     */
    public Storage(String dir, TaskList tasks) {
        this.currdir = dir;
        this.tasks = tasks;
    }

    /**
     * Loads tasks from the OUTPUT.txt file and adds them to the TaskList.
     *
     * @param parser The Parser instance for parsing task details.
     * @throws IOException If an I/O operation is interrupted.
     */
    public String load(Parser parser) throws IOException {
        StringBuilder results = new StringBuilder();
        String home = System.getProperty("user.home");
        Path outputPath = Paths.get(home, "OUTPUT.txt");
        Path archivePath = Paths.get(home, "Archive.txt");

        try (Scanner fileScanner = new Scanner(new File(outputPath.toString()))) {
            results.append("Your leftover tasks are:\n");
            int i = 0;
            while (fileScanner.hasNextLine()) {
                i++;
                String taskInfo = fileScanner.nextLine();
                boolean isDone = taskInfo.contains("[X] ");
                if (taskInfo.contains("[T] ")) {
                    String taskName = parser.taskNameFromTextFile(taskInfo, "[T] ");
                    Task newTask = new ToDos(taskName, isDone);
                    tasks.add(newTask);
                    results.append(i).append(". ").append(newTask).append("\n");
                } else if (taskInfo.contains("[E]")) {
                    String taskName = parser.taskNameFromTextFile(taskInfo, "[E] ");
                    String taskFrom = parser.taskFromFromTextFile(taskInfo);
                    String taskTo = parser.taskToFromTextFile(taskInfo);
                    Task newTask = new Events(taskName, taskFrom, taskTo, isDone);
                    tasks.add(newTask);
                    results.append(i).append(". ").append(newTask).append("\n");
                } else if (taskInfo.contains("[D]")) {
                    String taskName = parser.taskNameFromTextFile(taskInfo, "[D] ");
                    String taskBy = parser.taskByFromTextFile(taskInfo);
                    Task newTask = new Deadlines(taskName, taskBy, isDone);
                    tasks.add(newTask);
                    results.append(i).append(". ").append(newTask).append("\n");
                }
            }
        } catch (IOException e) {
            results.append("\nERROR: OUTPUT.txt file is not found in directory OUTPUT.txt!\n"
                    + "Creating OUTPUT.txt in the given directory now.");
        } finally {
            // Instance of PrintWriter to write new outputs to the file
            pw = new FileWriter(String.valueOf(outputPath), true);
            archive = new FileWriter(String.valueOf(archivePath), true);
        }
        return results.toString();
    }

    /**
     * Writes a task to the OUTPUT.txt file.
     *
     * @param task The task to be written.
     * @throws IOException If an I/O operation is interrupted.
     */
    public void write(Task task) throws IOException {
        pw.write(task.toString() + "\n");
        pw.flush();
    }

    /**
     * Adds the deleted task to the Archive.txt file.
     * 
     * @param task The task to be added to the Archive.txt file.
     * @throws IOException
     */
    public void archive(Task task) throws IOException {
        archive.write(task.toString() + "\n");
        archive.flush();
    }

    /**
     * Overwrites the OUTPUT.txt file with updated task information.
     *
     * @throws IOException If an I/O operation is interrupted.
     */
    public void overwrite() throws IOException {
        pw = new FileWriter(currdir);
        pw.flush();
        for (int i = 0; i < tasks.size(); i++) {
            write(tasks.retrieve(i));
            pw.flush();
        }
    }
}
