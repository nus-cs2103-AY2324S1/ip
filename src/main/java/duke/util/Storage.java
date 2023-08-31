package duke.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.CorruptHarddriveException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Provides functionality for storing tasks in a file and retrieving tasks from a file.
 */
public class Storage {

    /** File path to where the tasks are saved. */
    private static final String filePath = "./data/hardrive.txt";

    /** Directory path to where the file is located. */
    private static final String dataFolderPath = "./data";

    /**
     * Loads the tasks from the hard drive.
     *
     * @return An ArrayList of Task objects, each representing a task retrieved from the hard drive.
     *
     */
    public ArrayList<Task> load() {
        ArrayList<Task> store = new ArrayList<>();
        try {

            File f = new File(filePath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            int index = 0;
            while (s.hasNext()) {
                index++;
                String data = s.nextLine();
                String[] dataSplit = data.split("\\|");
                String type = dataSplit[0].trim();
                Task task;
                switch (type) {
                case "T":
                    if (dataSplit.length != 3) {
                        throw new CorruptHarddriveException();
                    }
                    task = new Todo(dataSplit[2].trim());
                    store.add(task);
                    if (Boolean.valueOf(dataSplit[1].trim())) {
                        task.mark();
                    }
                    break;
                case "D":
                    if (dataSplit.length != 4) {
                        throw new CorruptHarddriveException();
                    }
                    task = new Deadline(dataSplit[2].trim(), dataSplit[3].trim());
                    store.add(task);
                    if (Boolean.valueOf(dataSplit[1].trim())) {
                        task.mark();
                    }
                    break;
                case "E":
                    if (dataSplit.length != 5) {
                        throw new CorruptHarddriveException();
                    }
                    task = new Event(dataSplit[2].trim(), dataSplit[3].trim(), dataSplit[4].trim());
                    store.add(task);
                    if (Boolean.valueOf(dataSplit[1].trim())) {
                        task.mark();
                    }
                    break;
                default:
                    throw new CorruptHarddriveException();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No initial file");
        } catch (CorruptHarddriveException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return store;
    }

    /**
     * Saves the given list of tasks to the hard drive.
     *
     * @param store The list of tasks to save.
     */
    public void save(ArrayList<Task> store) {
        try {
            File dataFolder = new File(dataFolderPath);
            if (!dataFolder.exists()) {
                dataFolder.mkdirs();
            }

            FileWriter fw = new FileWriter(filePath);
            for (Task element : store) {
                String data = element.transformFormat();
                fw.write(data);
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}

