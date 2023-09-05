package seedu.duke.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import seedu.duke.tasks.Deadline;
import seedu.duke.tasks.Event;
import seedu.duke.tasks.Task;
import seedu.duke.tasks.Todo;

/**
 * Storage
 */
public class Storage {
    private String filePath;
    private final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * constructor for Storage class
     *
     * @param filePath file path of duke.txt
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Write the TaskList to file
     *
     * @param taskList TaskList to be written to duke.txt
     */
    public void writeFile(TaskList taskList) {
        try {
            // don't have to handle case of file path not existing as
            // during readFile (which occurs at start of script, checks if filepath exists)
            File file = new File(filePath);
            FileWriter writer = new FileWriter(file);
            for (Task task : taskList.returnTaskList()) {
                writer.write(task.writeFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error while saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Read from duke.txt file
     *
     * @return Tasklist
     * @throws IOException throws any error
     */
    public List<Task> readFile() throws IOException {
        // TaskList taskList = new TaskList(ui); // Create an empty task list
        List<Task> taskList = new ArrayList<>();
        try {
            File myData = new File(filePath);
            Scanner scanner = new Scanner(myData);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseTaskFromLine(line);
                if (task != null) {
                    // this adds to taskList without printing anything
                    //taskList.addAvailTasks(task);
                    taskList.add(task);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            if (new File("data").mkdir()) {
                System.out.println("data folder does not exist, create now");
            } else if (new File("data/duke.txt").createNewFile()) {
                System.out.println("duke.txt file not exist, create now");
            }
        }
        return taskList;
    }

    /**
     * helper function
     *
     * @param line line
     * @return Task
     */
    private Task parseTaskFromLine(String line) {
        String[] parts = line.split(" \\| ");
        String taskType = parts[0];
        String description = parts[2];
        boolean isMarked = parts[1].equals("1");

        switch (taskType) {
        case "T":
            return new Todo(description, isMarked);
        case "D":
            LocalDateTime byDate = LocalDateTime.parse(parts[3], timeFormat);
            return new Deadline(description, byDate, isMarked);
        case "E":
            LocalDateTime from = LocalDateTime.parse(parts[3], timeFormat);
            LocalDateTime to = LocalDateTime.parse(parts[4], timeFormat);
            return new Event(description, from, to, isMarked);
        // Add cases for other task types (e.g., Event) as needed
        default:
            return null; // Unknown task type, skip
        }
    }
}
