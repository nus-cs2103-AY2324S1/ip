package ratspeak.storage;

import ratspeak.exception.DukeException;
import ratspeak.task.Deadline;
import ratspeak.task.Event;
import ratspeak.task.Task;
import ratspeak.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {

    private static final String home = System.getProperty("user.home");
    private static final String DATA_FILE_PATH = home + "/data/duke.txt";
    private static final String DIRECTORY_PATH = home + "/data";

    /**
     * writes to hard disk
     * textToAdd is the storage text that was created by the tasks
     * method creates a text in the next line in the .txt file.
     * @param textToAdd text to be added to .txt file.
     * @throws DukeException if file is corrupted
     */
    public void writeToFile(String textToAdd) throws DukeException {
        createDataFile();
        updateFile(textToAdd);
    }

    private void createDataFile() throws DukeException {
        try{
            File theDir = new File(DIRECTORY_PATH);
            if (!theDir.exists()) {
                theDir.mkdirs();
                File dataFile = new File(DATA_FILE_PATH);
                dataFile.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    private void updateFile(String textToAdd) throws DukeException {
        try {
            File file = new File(DATA_FILE_PATH);
            FileWriter fw = new FileWriter(DATA_FILE_PATH, true);
            if (file.length() != 0) {
                fw.write(System.lineSeparator());
            }
            fw.write(textToAdd);

            fw.close();
        } catch(IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    private ArrayList<Task> addInfoFromFileToTaskList() throws DukeException {
        try {
            File dataFile = new File(DATA_FILE_PATH);
            Scanner sc = new Scanner(dataFile);
            ArrayList<Task> tasks = new ArrayList<Task>();
            while (sc.hasNext()) {
                String taskInfo = sc.nextLine();
                processLine(tasks, taskInfo);
            }
            return tasks;
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    private void processLine(ArrayList<Task> tasks, String taskInfo) throws DukeException {
        String taskName = taskInfo.substring(7);
        boolean marked = taskInfo.charAt(4) == 'X';

        if (taskInfo.charAt(1) == 'T') {
            Task todoTask = new Todo(taskName);
            if (marked) {
                todoTask = todoTask.done();
            }
            tasks.add(todoTask);
        } else if (taskInfo.charAt(1) == 'D') {
            Task deadlineTask = new Deadline(taskName);

            if (marked) {
                deadlineTask = deadlineTask.done();
            }
            tasks.add(deadlineTask);
        } else if (taskInfo.charAt(1) == 'E') {
            Task eventTask = new Event(taskName);
            if (marked) {
                eventTask = eventTask.done();
            }
            tasks.add(eventTask);
        }
    }

    /**
     * reads from duke.txt file and store the information as task into an array list of tasks.
     * method uses a scanner to read from duke.txt file, then adds the description into the task object.
     * Task object is then added into the array list of tasks
     * @return Arraylist of tasks that have been stored previously.
     */
    public ArrayList<Task> loadFromFile() {
        try {
            createDataFile();
            return addInfoFromFileToTaskList();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<Task>();

    }

    /**
     * Updates the .txt file using an arraylist of tasks.
     * @param tasklist arraylist of tasks
     * @throws DukeException if file is corrupted
     */
    public void writeAllToFile(ArrayList<Task> tasklist) throws DukeException {
        createDataFile();
        removeInfo();

        for (int i = 0; i < tasklist.size(); i++) {
            writeToFile(tasklist.get(i).storageText());
        }
    }

    private void removeInfo() throws DukeException {
        try {
            File file = new File(DATA_FILE_PATH);
            FileWriter writer = new FileWriter(file);
            writer.write("");
            writer.close();
        } catch(IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
