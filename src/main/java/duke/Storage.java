package duke;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.UnknownTaskTypeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * A class that deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private String filePath;

    /**
     * A constructor method for the Storage object.
     * @param filePath string representation of file path to database.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * A method that returns a Task ArrayList based on stored data. ArrayList
     * generated is then used to initialise TaskList object.
     * @return ArrayList based on stored data.
     * @throws Exception if file is corrupted.
     */
    public ArrayList<Task> load() throws Exception {
        ArrayList<Task> arr = new ArrayList<>();
        File f = new File(filePath);
        try {
            f.createNewFile();
            Scanner dataSc = new Scanner(f);
            while (dataSc.hasNextLine()) {
                addStoredTask(arr, dataSc);
            }
            dataSc.close();
        } catch (Exception e) {
            throw e;
        }
        return arr;
    }

    /**
     * A method to add a stored task to an array.
     * @param arr Array where task is to be added.
     * @param sc Scanner used to read storage file.
     * @throws Exception when storage file is not in a parseable format.
     */
    public void addStoredTask(ArrayList<Task> arr, Scanner sc) throws Exception {
        try {
            String[] task = sc.nextLine().trim().split("\\|");
            task[0] = task[0].trim();
            int taskType = task[0].equals("T") ? 0 : task[0].equals("D") ? 1 : 2;
            switch (taskType) {
            case 0: {
                String dataString = "todo " + task[2].trim();
                arr.add(Todo.of(dataString));
                break;
            }
            case 1: {
                String dataString = "deadline " + task[2].trim() + " /by " + task[3].trim();
                arr.add(Deadline.of(dataString));
                break;
            }
            case 2: {
                String dataString = "event " + task[2].trim() + " /from " + task[3].trim() + " /to "
                        + task[4].trim();
                arr.add(Event.of(dataString));
                break;
            }
            default: {
                throw new UnknownTaskTypeException();
            }
            }
            setMarkStatusForStoredTask(arr, task);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * A method that sets whether task is marked or not, based on its stored status.
     * @param arr Array where task resides.
     * @param taskStringArr Array of strings which represent stored task.
     */
    public void setMarkStatusForStoredTask(ArrayList<Task> arr, String[] taskStringArr) {
        if (taskStringArr[1].trim().equals("1")) {
                arr.get(arr.size() - 1).markAsDone();
            } else {
                arr.get(arr.size() - 1).markAsNotDone();
            }
    }

    /**
     * A method to update stored data based on latest user input on the program.
     * @param arr active TaskList object of the program.
     */
    public void update(TaskList arr) {
        try {
            FileOutputStream object = new FileOutputStream(this.filePath, false);
            for (char c : arr.createStorageString().toCharArray()) {
                object.write(c);
            }
            object.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
