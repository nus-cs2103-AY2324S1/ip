package arona.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

import arona.task.DeadlineTask;
import arona.task.EventTask;
import arona.task.Task;
import arona.task.ToDoTask;

/**
 * The `Storage` class is responsible for loading, saving, and updating tasks in a file.
 */
public class Storage {
    private Path path;
    private File inFile;

    /**
     * Initializes a new instance of the `Storage` class with the specified file path.
     *
     * @param filePath The path to the storage file.
     */
    public Storage(String filePath) {
        assert filePath != null : "File path cannot be null";
        this.path = Paths.get(filePath);
        this.inFile = path.toFile();
        assert inFile.exists() : "Storage file does not exist";
    }


    /**
     * Loads tasks from the storage file and populates the given tasks list.
     *
     * @param tasks The list of tasks to populate with data from the storage file.
     */
    public void loadTasks(ArrayList<Task> tasks) {
        try {
            if (!inFile.exists()) {
                inFile.createNewFile();
            }

            BufferedReader br = new BufferedReader(new FileReader(path.toString()));

            String line;
            while ((line = br.readLine()) != null) {
                String[] strings = line.split("\\|");
                switch (strings[0]) {
                case "T":
                    ToDoTask todoTask = new ToDoTask(strings[2], Integer.parseInt(strings[1]));
                    tasks.add(todoTask);
                    break;
                case "D":
                    DeadlineTask deadlineTask =
                            new DeadlineTask(strings[2], LocalDate.parse(strings[3]), Integer.parseInt(strings[1]));
                    tasks.add(deadlineTask);
                    break;
                case "E":
                    EventTask eventTask =
                            new EventTask(strings[2], strings[3], strings[4], Integer.parseInt(strings[1]));
                    tasks.add(eventTask);
                    break;
                default:
                    break;
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }


    /**
     * Updates the status of a task as marked (completed) in the storage file.
     *
     * @param taskIndex The index of the task to be marked.
     */
    public void updateTaskStatusAsMarked(int taskIndex) {
        assert taskIndex >= 0 : "Invalid task index";
        try {
            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

            BufferedReader br = new BufferedReader(new FileReader(inFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

            String line;
            int currentLine = 0;

            while ((line = br.readLine()) != null) {
                if (currentLine != taskIndex) {
                    bw.write(line);
                    bw.newLine();
                } else {
                    char[] charArray = line.toCharArray();
                    charArray[2] = '1';
                    String modifiedLine = new String(charArray);
                    bw.write(modifiedLine);
                    bw.newLine();
                }
                currentLine++;
            }

            br.close();
            bw.close();

            inFile.delete();
            tempFile.renameTo(inFile);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Updates the status of a task as unmarked in the storage file.
     *
     * @param taskIndex The index of the task to be unmarked.
     */
    public void updateTaskStatusAsUnmarked(int taskIndex) {
        assert taskIndex >= 0 : "Invalid task index";
        try {
            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

            BufferedReader br = new BufferedReader(new FileReader(inFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

            String line;
            int currentLine = 0;

            while ((line = br.readLine()) != null) {
                if (currentLine != taskIndex) {
                    bw.write(line);
                    bw.newLine();
                } else {
                    char[] charArray = line.toCharArray();
                    charArray[2] = '0';
                    String modifiedLine = new String(charArray);
                    bw.write(modifiedLine);
                    bw.newLine();
                }
                currentLine++;
            }

            br.close();
            bw.close();

            inFile.delete();
            tempFile.renameTo(inFile);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Saves a to-do task to the storage file.
     *
     * @param todoTask The to-do task to be saved.
     */
    public void saveTask(ToDoTask todoTask) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(inFile, true));

            String data = "T|0|" + todoTask.getDescription() + "\n";

            bw.write(data);
            bw.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Saves a deadline task to the storage file.
     *
     * @param deadlineTask The deadline task to be saved.
     */
    public void saveTask(DeadlineTask deadlineTask) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(inFile, true));

            String data = "D|0|" + deadlineTask.getDescription() + "|" + deadlineTask.getDate() + "\n";

            bw.write(data);
            bw.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Saves an event task to the storage file.
     *
     * @param eventTask The event task to be saved.
     */
    public void saveTask(EventTask eventTask) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(inFile, true));

            String data = "E|0|" + eventTask.getDescription() + "|"
                    + eventTask.getFrom() + "|" + eventTask.getTo() + "\n";

            bw.write(data);
            bw.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Deletes a task from the storage file based on its index.
     *
     * @param taskIndex The index of the task to be deleted.
     */
    public void deleteTask(int taskIndex) {
        assert taskIndex >= 0 : "Invalid task index";
        try {
            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

            BufferedReader br = new BufferedReader(new FileReader(inFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

            String line = null;
            int currentLine = 0;

            while ((line = br.readLine()) != null) {
                if (currentLine != taskIndex) {
                    bw.write(line);
                    bw.newLine();
                }
                currentLine++;
            }

            br.close();
            bw.close();

            inFile.delete();
            tempFile.renameTo(inFile);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
