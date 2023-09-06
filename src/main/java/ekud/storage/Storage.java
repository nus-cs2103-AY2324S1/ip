package ekud.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;

import ekud.exceptions.EkudIOException;
import ekud.parser.Parser;
import ekud.tasks.Deadline;
import ekud.tasks.Event;
import ekud.tasks.TaskList;
import ekud.tasks.ToDo;

/**
 * The Storage class deals with handling the saved tasks file on the hard disk, by loading its contents
 * into the chatbot upon startup (or creating a new dir/file if it doesn't exist), and updating its
 * contents after the user is done with the chatbot.
 */
public class Storage {
    private final String path;
    private final File savedTasks;

    /**
     * Constructor for Storage, which initialises the directory and file to save tasks in.
     * @param path Filepath for the saved tasks file.
     * @throws EkudIOException Exception involving the creation of the saved tasks file.
     */
    public Storage(String path) {
        this.path = path;
        File file = new File(path);
        try {
            if (!file.getParentFile().exists()) { // Create directory if it doesn't exist
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) { // Create file if it doesn't exist
                System.out.println("Creating task file...");
                file.createNewFile();
                System.out.println("Task file created successfully");
            }
        } catch (IOException e) {
            System.out.println("Error with creating task file: " + e);
        } finally {
            this.savedTasks = file;
        }
    }

    /**
     * Loads saved tasks from the file into the chatbot's TaskList.
     * @param taskList The chatbot's TaskList to load tasks into.
     * @throws EkudIOException Exception involving improper loading of saved tasks into TaskList.
     */
    public String loadData(TaskList taskList) throws EkudIOException {
        Parser parser = new Parser(); // For parsing dateTime
        taskList.clear();
        try {
            Scanner scanner = new Scanner(this.savedTasks);
            int curTaskIndex = 0;
            int numDoneTasks = 0;
            while (scanner.hasNextLine()) {
                // Saved tasks format eg:
                // T |   | task1
                // D | X | task2 | 1st Sep
                // E |   | task 3 | 1st Sep 2pm | 3rd Sep 2pm
                String[] taskDetails = scanner.nextLine().split(" \\| ");
                String taskType = taskDetails[0];
                boolean isDone = taskDetails[1].equals("X");
                if (taskType.equals("T")) {
                    taskList.addTask(new ToDo(taskDetails[2]));
                } else if (taskType.equals("D")) {
                    LocalDateTime dateTime = parser.parseSavedDateTime(taskDetails[3]);
                    taskList.addTask(new Deadline(taskDetails[2], dateTime));
                } else if (taskType.equals("E")) {
                    LocalDateTime fromDateTime = parser.parseSavedDateTime(taskDetails[3]);
                    LocalDateTime toDateTime = parser.parseSavedDateTime(taskDetails[4]);
                    taskList.addTask(new Event(taskDetails[2], fromDateTime, toDateTime));
                }
                if (isDone) {
                    taskList.markDoneOnStart(curTaskIndex);
                    numDoneTasks++;
                }
                curTaskIndex++;
            }
            String response;
            if (curTaskIndex == 0) {
                response = "[No previous tasks saved]";
            } else {
                response = String.format(
                        "[You currently have (%d) unfinished task(s)]",
                        curTaskIndex - numDoneTasks);
            };
            return response;
        } catch (IOException e) {
            throw new EkudIOException("Error with loading saved tasks: " + e);
        } catch (IndexOutOfBoundsException e) {
            throw new EkudIOException("Error with parsing saved tasks: " + e);
        }
    }

    /**
     * Updates the saved task file with the latest tasks when the program ends.
     * @param taskList The chatbot's TaskList to save tasks from.
     * @throws EkudIOException Exception involving improper saving of tasks.
     */
    public String saveData(TaskList taskList) throws EkudIOException {
        try {
            FileWriter fw = new FileWriter(this.path);
            int len = taskList.getSize();
            for (int i = 0; i < len; i++) {
                fw.write(taskList.getSaveTaskFormat(i) + "\n");
            }
            fw.close();
            return String.format("[(%d) task(s) saved successfully]", len);
        } catch (IOException e) {
            throw new EkudIOException("Error with saving tasks: " + e);
        }
    }

}
