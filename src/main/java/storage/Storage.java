package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import duke.Parser;
import taskutil.Deadline;
import taskutil.Event;
import taskutil.Task;
import taskutil.TaskList;
import taskutil.Todo;

/**
 * Contains methods to create and edit file directory for storing and loading chatbot data.
 */
public class Storage {

    private static final String FILE_NAME = "TaskData.txt";

    private final String fileDirectory;
    private final String fileLocation; // fileLocation concatenates fileDirectory and FILE_NAME.

    /**
     * Constructor for Storage object, containing location of data tile.
     *
     * @param fileDirectory File location of data file.
     */
    public Storage(String fileDirectory) {
        this.fileDirectory = fileDirectory;
        this.fileLocation = fileDirectory + "/" + FILE_NAME;
    }

    /**
     * Creates directory and txt file for storing task data if they do not exist.
     *
     * @return True if directory and txt file exist/created successfully.
     */
    private boolean openFile() {
        File directory = new File(fileDirectory);
        File dataFile = new File(fileDirectory + "/" + FILE_NAME);
        try {
            if (directory.mkdir() && dataFile.createNewFile()) {
                System.out.println("File to store task data have been created and stored at:\n       "
                        + fileDirectory + "/" + FILE_NAME);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Writes data from ArrayList of tasks to a file.
     *
     * @param taskList An ArrayList of tasks.
     */
    public void writeToFile(TaskList taskList) {
        try {
            FileWriter file = new FileWriter(fileLocation);
            file.write(taskList.listToStringData());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads task data from txt file to chatbot.
     *
     * @return String representing result of loading data from file.
     */
    public String loadFromFile(TaskList taskList) {
        if (!this.openFile()) {
            return "An error occured where the file cannot be read";
        }
        assert taskList != null : "TaskList loading in data cannot be a null object.";
        // Variable is initialised here instead of try block for error printing.
        String data = "";
        try {
            File dataFile = new File(fileLocation);
            Scanner reader = new Scanner(dataFile);

            while (reader.hasNextLine()) {
                data = reader.nextLine();
                String[] splitInput = data.split(" \\| ");
                boolean isDone = splitInput[1].equals("X");

                // Data is in format [type, isDone, title, from/by, to], from/by/to are only present depending on type.
                Task currentTask;
                switch(splitInput[0]) {
                case "T":
                    currentTask = new Todo(splitInput[2].trim());
                    break;
                case "D":
                    currentTask = new Deadline(splitInput[2].trim(), Parser.parseDate(splitInput[3]));
                    break;
                case "E":
                    LocalDateTime start = Parser.parseDate(splitInput[3]);
                    LocalDateTime end = Parser.parseDate(splitInput[4]);
                    currentTask = new Event(splitInput[2].trim(), start, end);
                    break;
                default:
                    return String.format("Unknown symbol [%s] detected, the line will be overwritten:\n     [%s]",
                            splitInput[0], data);
                }
                currentTask.setStatus(isDone);
                taskList.addTask(currentTask, false);
            }
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            // File formatted with wrong no. of " | " dividers for task types, or wrong date format.
            return "There seems to be a problem with reading in data from:\n      ["
                    + fileLocation
                    + "]\n\nThe following line has an error and will be overwritten:\n     ["
                    + data + "]";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "All data from file has been loaded successfully :D";
    }
}
