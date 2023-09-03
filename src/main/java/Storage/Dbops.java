package Storage;

import Exceptions.DukeCorruptedDataException;
import Exceptions.DukeInvalidDateTimeException;
import Models.*;

import java.io.*;
import java.util.Objects;

import static LogicHandlers.Parsers.DateTimeParser.parseDateTimeFromString;
import static Ui.BasicOutputPrinter.printBasicOutput;
import static Ui.ErrorOutputPrinter.printErrorOutput;
import static java.lang.System.exit;

/**
 * Dbops (Database Operations) Class:
 * Handles all writing and retrieval from the database, memory.txt.
 */
public class Dbops {

    /**
     * A constant that holds the path to the memory file.
     */
    private static final String FILEPATH = "src/data/";

    /**
     * A constant that holds the name of the memory file.
     */
    private static final String FILENAME = "memory.txt";

    /**
     * Initializes the Task Array and loads data from memory file, or initializes a memory file if not found.
     *
     * @return The initialized TaskArray.
     */
    public static TaskArray initializeDatabase() {
        TaskArray tasks  = new TaskArray();
        loadTasksFromFile(tasks);
        return tasks;
    }

    /**
     * Creates the memory file, based on the filepath and filename.
     */
    private static void createMemoryFile() {
        File newFile = new File(Dbops.FILEPATH + Dbops.FILENAME);

        try {
            if (newFile.createNewFile()) {
                System.out.println("Memory file created successfully.");
            } else {
                System.err.println("Memory file already exists.");
            }
        } catch (IOException e) {
            printErrorOutput("An error occurred while creating memory file: " + e.getMessage());
            exit(1);
        }
    }

    /**
     * Deletes the memory file, based on the filepath and filename.
     */
    private static void deleteMemoryFile() {
        File fileToDelete = new File(Dbops.FILEPATH + Dbops.FILENAME);

        if (fileToDelete.delete()) {
            System.out.println("Old memory file deleted successfully.");
        } else {
            System.err.println("Failed to delete the old memory file.");
        }
    }

    /**
     * Loads tasks from the memory file.
     *
     * @param taskArray The TaskArray to load the tasks into.
     */
    public static void loadTasksFromFile(TaskArray taskArray) {
        try (BufferedReader reader = new BufferedReader(new FileReader(Dbops.FILEPATH + Dbops.FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Create Task from each line and add them to the list
                String[] parts = line.split(",");
                boolean isMarked = Objects.equals(parts[2], "true");

                if (Objects.equals(parts[0], "Todo") && parts.length == 3) {
                    taskArray.add(new ToDo(parts[1], isMarked));
                } else if (Objects.equals(parts[0], "Deadline") && parts.length == 4) {
                    taskArray.add(new Deadline(parts[1], isMarked, parseDateTimeFromString(parts[3])));
                } else if (Objects.equals(parts[0], "Event") && parts.length == 5) {
                    taskArray.add(new Event(parts[1], isMarked, parseDateTimeFromString(parts[3]), parseDateTimeFromString(parts[4])));
                } else {
                    throw new DukeCorruptedDataException("Error: file is corrupted. Failed to load data from file.");
                }
            }
        } catch (IOException e) {
            String output = "File not found: " + Dbops.FILENAME + "\n" +
                    "If this is your first day, welcome!\n" +
                    "A new memory file, " + Dbops.FILENAME + " has been created.";

            printBasicOutput(output);
            Dbops.createMemoryFile();

        } catch (DukeCorruptedDataException | DukeInvalidDateTimeException e) {
            printErrorOutput(e + "\n");
            Dbops.deleteMemoryFile();
            Dbops.createMemoryFile();
            printBasicOutput("A new memory file, " + Dbops.FILENAME + " has been created.");

        }
    }
    /**
     * Saves all tasks to the memory file.
     *
     * @param taskArray The TaskArray to load the tasks from.
     */
    public static void saveTasksToFile(TaskArray taskArray) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Dbops.FILEPATH + Dbops.FILENAME))) {
            for (Task task : taskArray) {
                // Format and write each task to the file
                writer.write(task.getTaskDetails());
                writer.newLine();
            }
        } catch (IOException e) {
            printErrorOutput("Error saving tasks to memory file: " + e.getMessage());
        }
    }
}
