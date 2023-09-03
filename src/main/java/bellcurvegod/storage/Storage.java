package bellcurvegod.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import bellcurvegod.exception.WrongDataFormatException;
import bellcurvegod.task.Deadline;
import bellcurvegod.task.Event;
import bellcurvegod.task.Task;
import bellcurvegod.task.Todo;
import bellcurvegod.tasklist.TaskList;

/**
 * Encapsulates the storage to store and manipulate data.
 */
public class Storage {
    private static final String DATA_DIRECTORY_PATH = "./data";
    private static final String DATA_FILE_PATH = "./data/tasks.txt";

    private static final ArrayList<Task> tasks = TaskList.getTaskList();

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Loads the tasks data stored on the disk.
     *
     * @param filePath Path of the data file.
     * @throws IOException If unable to create missing file.
     */
    public static void loadTasks(String filePath) throws IOException {
        try {
            readFile(filePath);
        } catch (FileNotFoundException e) {
            createMissingFile(filePath);
        }
    }

    /**
     * Reads data from the file.
     *
     * @param filePath Path of the data file.
     * @throws FileNotFoundException If file not found.
     */
    public static void readFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            try {
                handleTaskData(sc.nextLine());
            } catch (WrongDataFormatException e) {
                System.out.println(e);
            }
        }
        sc.close();
    }

    /**
     * Creates missing file (and directory if not found).
     *
     * @param filePath Path to create missing file at.
     * @throws IOException If unable to create file.
     */
    public static void createMissingFile(String filePath) throws IOException {
        // System.out.println("File not found!\nFile has been created!");
        File dir = new File(DATA_DIRECTORY_PATH);
        if (!dir.exists()) {
            dir.mkdir();
        }
        File f = new File(filePath);
        f.createNewFile();
    }

    /**
     * Adds new tasks to the taskList from parsing the data file.
     *
     * @param taskData Data in the data file.
     * @throws WrongDataFormatException If data is in the wrong format.
     */
    public static void handleTaskData(String taskData) throws WrongDataFormatException {
        String[] words = taskData.split("\\|");
        String taskType = words[0];
        String status = words[1];
        String description = words[2];
        boolean isDone = status.equals("1");

        switch (taskType) {
        case "T":
            tasks.add(new Todo(description, isDone));
            TaskList.incrementNumOfTasks();
            break;
        case "D":
            String ddl = words[3];

            tasks.add(new Deadline(description, LocalDate.parse(ddl), isDone));
            TaskList.incrementNumOfTasks();
            break;
        case "E":
            String fromTime = words[4];
            String toTime = words[5];

            tasks.add(new Event(description, LocalDateTime.parse(fromTime), LocalDateTime.parse(toTime), isDone));
            TaskList.incrementNumOfTasks();
            break;
        default:
            throw new WrongDataFormatException("The tasks stored in your local disk have wrong format!");
        }
    }

    /**
     * Rewrites the data file with current content in the taskList.
     *
     * @throws IOException If unable to write to the file.
     */
    public static void updateData() throws IOException {
        String data = "";
        for (Task t : tasks) {
            data += t.getDataRepresentation() + "\n";
        }
        writeToFile(DATA_FILE_PATH, data);
    }
}
