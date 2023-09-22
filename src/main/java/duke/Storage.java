package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;


/**
 * Handles the storage and loading of the text file.
 */
public class Storage {

    private String filepath;


    /**
     * Constructor for storage.
     * @param filepath the filepath of the file that will be used for storage.
     */

    public Storage(String filepath) {
        assert !filepath.equals("");
        this.filepath = filepath;
    }


    /**
     * Creates the file and loads tasks from the file to the taskList.
     * @throws IOException IO exception thrown.
     */

    public TaskList saveTasks() throws IOException {
        try {
            if (!Files.isDirectory(Paths.get("data/"))) {
                Files.createDirectories(Paths.get("data/"));
            }

            if (!Files.exists(Paths.get("data/duke.txt"))) {
                Files.createFile(Paths.get("data/duke.txt"));
                System.out.println("New file created");
            }
            if (Files.exists(Paths.get("data/duke.txt"))) {
                System.out.println("File exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        TaskList tasks = new TaskList();
        addTaskToList(tasks);
        return tasks;
    }

    /**
     * Adds task from file to tasklist.
     * @param tasks tasklist that tasks is loaded into.
     * @throws IOException IOException
     */
    public void addTaskToList(TaskList tasks) throws IOException {
        Scanner scanner = new Scanner(new File("data/duke.txt"));
        while (scanner.hasNext()) {
            String[] split = scanner.nextLine().split("\\|");
            for (int i = 0; i < split.length; i++) {
                split[i] = split[i].strip();
            }
            String description = split[2];
            boolean isDone = split[1].equals("X");
            switch (split[0]) {
            case "T":
                tasks.addTask(new ToDo(description, isDone));
                break;
            case "D":
                tasks.addTask(new Deadline(description, split[3], isDone));
                break;
            case "E":
                tasks.addTask(new Event(description, split[3], split[4], isDone));
                break;
            default:
                System.out.println("Oop! There is nothing here");
            }
        }
    }
    /**
     * Writes the data from the tasklist into the file.
     * @param tasks TaskList being used to store the data.
     * @throws IOException IO exception thrown.
     */

    public void writeTasksToFile(TaskList tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(filepath);
        for (int i = 0; i < tasks.getSize(); i++) {
            fileWriter.write(tasks.getTask(i).toWriteString() + "\n");
        }
        fileWriter.close();
    }

}

