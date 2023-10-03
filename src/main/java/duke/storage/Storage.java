package duke.storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeNotTaskException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;


/**
 * A Class to handle file reading and writing.
 *
 * @author marioalvaro
 */
public class Storage {
    private static final String PATH_FILE = "./data/TaskStorage.txt";
    private static final String PATH_DIRECTORY = "./data";
    private File file = new File(PATH_FILE);

    /**
     * A method to read the txt file
     * @return an ArrayList of Task
     * @throws Exception of the scanner and DukeNotTaskException
     */
    public ArrayList<Task> readFile() throws Exception {
        Scanner sc = new Scanner(file);
        ArrayList<Task> readFile = new ArrayList<>();

        while (sc.hasNextLine()) {
            String scanned = sc.nextLine();

            String[] singleTaskArray = scanned.split(" / ");

            Task task;
            switch (singleTaskArray[0]) {
            case "T":
                task = new Todo(singleTaskArray[2]);
                break;
            case "D":
                task = new Deadline(singleTaskArray[2], singleTaskArray[3]);
                break;
            case "E":
                task = new Event(singleTaskArray[2], singleTaskArray[3], singleTaskArray[4]);
                break;
            default:
                throw new DukeNotTaskException(singleTaskArray[0]);
            }
            markTask(task, singleTaskArray[1]);

            readFile.add(task);
        }

        return readFile;
    }

    /**
     * Mark the task if applicable
     *
     * @param task The task that needs to be marked
     * @param marker either "0" or "1"
     */
    private void markTask(Task task, String marker) {
        if (marker.equals("1")) {
            task.mark();
        }
        assert marker.equals("0") || marker.equals("1")
                : "Wrong marking format in storage";
    }

    /**
     * A method to write the file with the existing TaskList.
     *
     * @param taskList the taskList that want to be written
     */
    public void writeFile(TaskList taskList) {
        String newData = arrayToDataString(taskList);

        try {
            FileWriter fileWriter = new FileWriter(PATH_FILE, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(newData);

            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    /**
     * A method to check if the file exist. If not, create a new .txt file.
     */
    public void checkFile() {
        try {
            File directory = new File(PATH_DIRECTORY);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File file = new File(PATH_FILE);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Convert TaskList into String that compatible with the format of storing.
     * @param taskList The taskList that want to be written
     * @return a String with storing format
     */
    public static String arrayToDataString(TaskList taskList) {
        String dataString = "";

        for (int i = 0; i < taskList.size(); i++) {
            dataString += taskList.get(i).toDataString() + "\n";
        }
        return dataString;
    }
}
