package extensions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * The Storage class deals with handling the saved tasks file on the hard disk, by loading its contents
 * into the chatbot upon startup (or creating a new dir/file if it doesn't exist), and updating its
 * contents after the user is done with the chatbot.
 */
public class Storage {
    private final String path;
    private final File savedTasks;
    public Storage(String path) throws EkudIOException {
        this.path = path;
        File file = new File(path);
        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                System.out.println("Creating task file...");
                file.createNewFile();
                System.out.println("Task file created successfully");
            }
            this.savedTasks = file;
        } catch(IOException e) {
            throw new EkudIOException("Error with creating task file: " + e);
        }
    }
    /**
     * Loads saved tasks from the file into the chatbot's TaskList.
     */
    public void loadData(TaskList taskList) throws EkudIOException {
        Parser parser = new Parser();
        System.out.println("Loading up saved tasks...");
        try {
            Scanner scanner = new Scanner(this.savedTasks);
            int curTask = 0;
            while (scanner.hasNext()) {
                // Saved tasks format eg:
                // T | 0 | task1
                // D | 1 | task2 | 1st Sep
                // E | 0 | task 3 | 1st Sep 2pm | 3rd Sep 2pm
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
                if (isDone) taskList.markDoneOnStart(curTask);
                curTask++;
            }
            if (curTask == 0) {
                System.out.println("No saved tasks found");
            } else {
                System.out.println("Saved tasks loaded successfully");
            }
        } catch (IOException e) {
            throw new EkudIOException("Error with loading saved tasks: " + e);
        } catch (IndexOutOfBoundsException e) {
            throw new EkudIOException("Error with parsing saved tasks: " + e);
        }
    }

    public void saveData(TaskList taskList) throws EkudIOException {
        try {
            FileWriter fw = new FileWriter(this.path);
            int len = taskList.getSize();
            for (int i = 0; i < len; i++) {
                fw.write(taskList.getSaveTaskFormat(i) + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new EkudIOException("Error with saving tasks");
        }
    }

}
