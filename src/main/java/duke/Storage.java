package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import tasks.TaskList;

/**
 * Saves and retrieves tasks created by the user.
 */
public class Storage {

    private final String FILENAME;

    /**
     * Constructor, initializes the file name indicated by the user.
     *
     * @param filename Name of file to read or write task data to or from.
     */
    public Storage(String filename) {
        this.FILENAME = filename;
    }

    /**
     * Obtains saved task data from data file and updates Duke with it.
     *
     * @return A TaskList containing all the previously saved tasks.
     */
    public TaskList retrieveSavedData() {
        try {
            ArrayList<String> arr = new ArrayList<>();
            File f = new File("data/" + FILENAME);
            if (!f.exists()) {
                return new TaskList();
            }
            Scanner s = new Scanner(f);

            while (s.hasNext()) {
                String str = s.nextLine();
                arr.add(str);
            }

            s.close();
            return populate(arr);

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return new TaskList();
        }
    }

    /**
     * Parses stored task data and populates a TaskList with it.
     *
     * @param arr An array of tasks in the stored string format.
     * @return A TaskList of the said tasks.
     */
    private TaskList populate(ArrayList<String> arr) {
        TaskList taskList = new TaskList();

        for (String str : arr) {
            String[] segmented = str.split("\\|");
            String taskType = segmented[0];
            int isMarked = Integer.parseInt(segmented[1]);
            String desc = segmented[2];
            String end;
            String start;

            switch (taskType) {
            case "T":
                taskList.addTodo(desc, isMarked);
                break;
            case "D":
                end = segmented[3];
                taskList.addDeadline(desc, end, isMarked);
                break;
            case "E":
                end = segmented[3];
                start = segmented[4];
                taskList.addEvent(desc, start, end, isMarked);
                break;
            default:
                break;
            }
        }

        return taskList;
    }

    /**
     * Creates a new data file or opens the existing one to update it with the latest tasks.
     * The format of the task string is "~tasktype~|~isMarked~|~desc~|~end~|~start~".
     * Task type is T/D/E and isMarked is 0 for false and 1 for true.
     *
     * @param taskList The TaskList containing the latest tasks.
     */
    public void saveChanges(TaskList taskList) {
        try {
            File directory = new File("data");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            FileWriter fileWriter = new FileWriter("data/" + FILENAME);
            fileWriter.write("");
            fileWriter.append(taskList.getTextFormattedString());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
