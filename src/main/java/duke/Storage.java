package duke;

import Exceptions.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * This class deals with loading tasks from ./data/duke.txt and saving tasks in one file.
 */
public class Storage {
    protected String directoryPath;
    protected String filePath;
    private static File hardDisk;
    private static Parser parser;
    /**
     * Constructor method for the duke.Storage class.
     * @param filePath The relative path of duke.txt.
     * @param dirPath the relative path to the data directory.
     */
    public Storage (String dirPath, String filePath) {
        this.directoryPath = dirPath;
        this.filePath = filePath;
        this.parser = new Parser();
        hardDisk = new File(this.filePath);
    }
    protected void checkHardDisk() {
        File dataDirectory = new File(this.directoryPath);
        if (!dataDirectory.exists()) {
            System.out.println("     OOPS! The data directory doesn't exist. I'll create one for you!");
            dataDirectory.mkdir();
        }
        if (!hardDisk.exists()) {
            System.out.println("     OOPS! The hard disk doesn't exist. I'll create one for you!");
            try {
                hardDisk.createNewFile();
                hardDisk.setReadable(true);
                hardDisk.setWritable(true);
            } catch (IOException e) {
                System.out.println("     Something went wrong, we couldn't create duke.txt");
            }
        }
    }
    // retrieves past tasks
    protected ArrayList<Task> loadTask() {
        ArrayList<Task> taskArray = new ArrayList<>();
        checkHardDisk();
        try {
            Scanner fileScanner = new Scanner(hardDisk);
            while (fileScanner.hasNext()) {
                String task = fileScanner.nextLine();
                String[] taskDetails = parser.storageSplit(task);
                String taskType = taskDetails[0];
                String taskStatus = taskDetails[1];
                String taskDescription = taskDetails[2];
                switch (taskType) {
                    case "T":
                        Todo addTodo = new Todo(taskDescription);
                        if (Objects.equals(taskStatus, "done")) {
                            addTodo.updateAsDone();
                        }
                        taskArray.add(addTodo);
                        break;
                    case "D":
                        Deadline addDeadline = new Deadline(taskDescription, taskDetails[3]);
                        if (Objects.equals(taskStatus, "Y")) {
                            addDeadline.markAsDone();
                        }
                        taskArray.add(addDeadline);
                        break;
                    case "E":
                        String[] timeDetails = parser.storageTimeSplit(taskDetails[3]);
                        Event addEvent = new Event(taskDescription, timeDetails[0], timeDetails[1]);
                        if (Objects.equals(taskStatus, "Y")) {
                            addEvent.markAsDone();
                        }
                        taskArray.add(addEvent);
                        break;
                    default:
                        throw new DukeException("     invalid task in the hard disk");
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("     There is no saved duke.txt");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return taskArray;
    }
    // saves new tasks
    protected void saveTask(ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        for (Task task : taskList) {
            String writeTask = task.getDescription() + "\n";
            fw.write(writeTask);
        }
        fw.close();
    }
}
