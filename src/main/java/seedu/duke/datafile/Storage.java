package seedu.duke.datafile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import seedu.duke.exceptions.InvalidDeadlineException;
import seedu.duke.exceptions.InvalidEventException;
import seedu.duke.exceptions.LemonException;
import seedu.duke.tasks.Deadline;
import seedu.duke.tasks.Event;
import seedu.duke.tasks.Task;
import seedu.duke.tasks.Todo;

/**
 * The Storage class is responsible for handling the storage and retrieval of tasks
 * in a text file. It provides methods to save tasks to a file and load tasks from a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a new Storage object with a default file path.
     * The default file path is "./datafile/tasks.txt".
     */
    public Storage() {
        this.filePath = "./datafile/tasks.txt";
    }

    /**
     * Constructs a new Storage object with a custom file path.
     *
     * @param filePath The path of the file where tasks will be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves a list of tasks to the specified file.
     *
     * @param tasks An ArrayList of Task objects to be saved.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                System.out.println("Creating new file");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            writeToFile(filePath, "");
            if (tasks.size() > 0) {
                for (int i = 0; i < tasks.size(); i++) {
                    addToFile(filePath, tasks.get(i).addDataFormat() + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Issues saving tasks to storage.");
        }
    }
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void addToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd + System.lineSeparator());
        fw.close();
    }

    /**
     * Loads tasks from the data file and returns them as an ArrayList of Tasks.
     *
     * @return An ArrayList of Task objects loaded from the file.
     * @throws LemonException If the file cannot be found or there is an issue
     *                       loading tasks from the file.
     */
    public ArrayList<Task> loadFile() throws LemonException {
        ArrayList<Task> tasks = new ArrayList<>();
        File dataFile = new File(filePath);
        Scanner scanner;
        // if there is no storage - no tasks
        if (!dataFile.exists()) {
            System.out.println("File doesn't exist?");
            return tasks;
        } else {
            try {
                scanner = new Scanner(dataFile);
                while (scanner.hasNextLine()) {
                    String[] input = scanner.nextLine().split(" \\| ");
                    String taskType = input[0];
                    switch(taskType) {
                    case "T":
                        boolean isDoneT = checkDone(input[1]);
                        String descriptionT = input[2];
                        Task toAddTaskT = new Todo(descriptionT, isDoneT);
                        tasks.add(toAddTaskT);
                        break;
                    case "D":
                        boolean isDoneD = checkDone(input[1]);
                        String descriptionD = input[2];
                        String by = input [3];
                        try {
                            Task toAddTaskD = new Deadline(descriptionD, by, isDoneD);
                            tasks.add(toAddTaskD);
                        } catch (InvalidDeadlineException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "E":
                        boolean isDoneE = checkDone(input[1]);
                        String descriptionE = input[2];
                        String fromE = input[3];
                        String toE = input[4];
                        try {
                            Task toAddTaskE = new Event(descriptionE, fromE, toE, isDoneE);
                            tasks.add(toAddTaskE);
                        } catch (InvalidEventException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    default:
                        throw new LemonException("Failure to load file!");
                    }
                }
            } catch (FileNotFoundException e) {
                throw new LemonException("Storage file not available!");
            }
        }
        return tasks;
    }

    private static boolean checkDone(String isDone) {
        return isDone.equals("1");
    }



}
