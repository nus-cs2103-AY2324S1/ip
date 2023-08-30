package duke.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.Duke;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * duke.util.Storage class that deals with loading tasks from the file and saving tasks in the file.
 *
 * @author Pearlynn
 */

public class Storage {
    private final String pathname;

    /**
     * Constructor for duke.util.Storage class.
     *
     * @param pathname The pathname of the file.
     */
    public Storage(String pathname) {
        this.pathname = pathname;
    }

    /**
     * Loads data from the file.
     *
     * @return An array list of the tasks.
     */
    public ArrayList<Task> loadData() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            File file = new File(this.pathname);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                list.add(readData(data));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("No file found.");
        }
        return list;
    }

    /**
     * Reads data from each line of the file.
     *
     * @param line The line in the file.
     * @return A Task that the line represents.
     */
    private Task readData(String line) {
        Task task = null;
        String[] data = line.split(" \\| ");
        String taskType = data[0];
        boolean isDone = Integer.parseInt(data[1]) == 1;
        String description = data[2];
        try {
            switch (taskType) {
            case "T":
                task = new Todo(description, isDone);
                break;
            case "D":
                task = new Deadline(description, isDone, data[3]);
                break;
            case "E":
                String[] time = data[3].split(" - ");
                task = new Event(description, isDone, time[0], time[1]);
                break;
            default:
                throw new DukeException("☹ OOPS!!! Invalid task type");
            }
        } catch (DateTimeParseException e) {
            Duke.getUi().printDateTimeParseException();
        } catch (DukeException e) {
            Duke.getUi().showError(e.getMessage());
        }
        return task;
    }

    /**
     * Saves data to the file.
     *
     * @param list The task list.
     */
    public void saveData(ArrayList<Task> list) {
        try {
            FileWriter fileWriter = new FileWriter(this.pathname);
            for (Task t : list) {
                String str = t.taskStringify();
                fileWriter.write(str + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
