package duke;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The `Storage` class handles file input and output operations for Duke task data.
 * It is responsible for creating data storage, loading tasks from a file,
 * saving tasks to a file, deleting a specific line from the file,
 * and updating a specific line in the file.
 */
public class Storage {
    private static final String FILE_PATH = "src/main/data/duke.txt";

    /**
     * Creates the data storage directory and file if they do not exist.
     * If the directory or file creation fails, appropriate error messages are displayed.
     */
    public static void createDataLocation() {
        File dir = new File("src/main/data");

        // Check if the directory exists; if not, create it
        if (!dir.exists()) {
            if (dir.mkdirs()) {
                System.out.println("Directory created: " + dir.getAbsolutePath());
            } else {
                System.err.println("Failed to create directory: " + dir.getAbsolutePath());
                return;
            }
        }

        File db = new File(FILE_PATH);

        // Check if the file exists; if not, create it
        if (!db.exists()) {
            try {
                if (db.createNewFile()) {
                    System.out.println("File created: " + db.getAbsolutePath());
                } else {
                    System.err.println("Failed to create file: " + db.getAbsolutePath());
                }
            } catch (IOException e) {
                System.out.println("Error when creating the data storage!");
            }
        }
    }

    /**
     * Loads tasks from a file and populates a given `TaskList` with the loaded tasks.
     * The file is read line by line, and tasks are parsed and added to the list.
     * If the file is not found or an error occurs during loading,
     * appropriate error messages are displayed.
     *
     * @param taskList The `TaskList` to populate with loaded tasks.
     */
    public static void loadTasksFromFile(TaskList taskList) {
        List<Task> loadedTasks = new ArrayList<>(); // Create a temporary list

        try (Scanner scanner = new Scanner(new File(FILE_PATH))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                if (parts.length < 3) {
                    continue;
                }
                String taskType = parts[0];
                boolean isDone = Integer.parseInt(parts[1]) == 1;
                String taskDescription = parts[2];

                // Check if the task is already in duke.Duke.allTasks
                if (!TaskList.isTaskInAllTasks(taskType, taskDescription)) { //rewrite method in duke.TaskList class
                    Task task;

                    if (taskType.equals("T")) {
                        task = new Todo(taskDescription, false);
                    } else if (taskType.equals("D") && parts.length >= 4) {
                        String by = parts[3];
                        task = new Deadline(taskDescription, false, by);
                    } else if (taskType.equals("E") && parts.length >= 4) {
                        String from = parts[3];
                        String to = (parts.length > 4) ? parts[4] : "";
                        task = new Event(taskDescription, false, from, to);
                    } else {
                        continue;
                    }

                    if (isDone) {
                        task.setStatus(TaskStatus.DONE);
                    }

                    loadedTasks.add(task);
                }
            }

            taskList.getTasks().clear();
            taskList.getTasks().addAll(loadedTasks);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (DukeException e) {

        }
    }

    /**
     * Saves a task to the file by appending it as a new line.
     * If an error occurs during saving, an appropriate error message is displayed.
     *
     * @param task The task to save to the file.
     */
    public static void saveTaskToFile(String task) {
        try (FileWriter fw = new FileWriter(FILE_PATH, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(task);
        } catch (IOException e) {
            System.err.println("Error saving task to file: " + e.getMessage());
        }
    }

    /**
     * Deletes a specific line from the file.
     * If an error occurs during deletion, an appropriate error message is displayed.
     *
     * @param lineNumber The line number to delete from the file.
     */
    public static void deleteLineFromFile(int lineNumber) {
        try {
            File inputFile = new File(FILE_PATH);
            File tempFile = new File(FILE_PATH + ".tmp");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;
            int lineCounter = 0;

            while ((currentLine = reader.readLine()) != null) {
                lineCounter++;

                if (lineCounter == lineNumber) {
                    continue; // Skip the line to be deleted
                }

                writer.write(currentLine);
                writer.newLine();
            }

            writer.close();
            reader.close();

            boolean successfulRename = tempFile.renameTo(inputFile);

            if (!successfulRename) {
                throw new IOException("Failed to rename temporary file");
            }
        } catch (IOException e) {
            System.err.println("Error deleting line from file: " + e.getMessage());
        }
    }

    /**
     * Updates a specific line in the file with new content.
     * If an error occurs during updating, an appropriate error message is displayed.
     *
     * @param lineNumber     The line number to update in the file.
     * @param updatedContent The new content to replace the line with.
     */
    public static void updateLineInFile(int lineNumber, String updatedContent) {
        try (RandomAccessFile file = new RandomAccessFile(FILE_PATH, "rw")) {
            long position = 0;
            int currentLine = 1;

            while (currentLine < lineNumber) {
                String line = file.readLine();
                if (line == null) {
                    System.err.println("Invalid line number: " + lineNumber);
                    return;
                }
                position += line.length() + System.lineSeparator().length();
                currentLine++;
            }

            file.seek(position);

            file.writeBytes(updatedContent);

        } catch (IOException e) {
            System.err.println("Error updating line in file: " + e.getMessage());
        }
    }

}
