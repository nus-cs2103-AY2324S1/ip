package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.*;

/**
 * Storage class to load and write data from or to
 * an external file.
 */
public class Storage {
    /** The file path for the Storage object. */
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates a file.
     *
     * @throws DukeException If any error occurs.
     */
    private void createFile() throws DukeException {
        try {
            String[] temp = filePath.split("/");
            createDirectory(temp);
            File file = new File(filePath);
            file.createNewFile();
        } catch (IOException e) {
            throw new DukeException(" ☹ I/O Error!");
        }
    }

    /**
     * Creates directories for the file to be created.
     *
     * @param filePathArray An array of Strings, split via filePath.
     */
    private void createDirectory(String[] filePathArray) {
        String directoryPath = "";
        for (int i = 0; i < filePathArray.length - 1; i++) {
            directoryPath += filePathArray[i];
            File directory = new File(directoryPath);
            assert !directory.exists();
            if (!directory.isDirectory()) {
                directory.mkdir();
            }
            directoryPath += "/";
        }
    }

    /**
     * Loads the file.
     *
     * @return A File object.
     * @throws DukeException If any error occurs.
     */
    private File loadFile() throws DukeException {
        File file = new File(filePath);
        if (!file.exists()) {
            createFile();
        }
        return file;
    }

    /**
     * Writes a String into an external file.
     * Rewrites the content of the file if the file exists.
     *
     * @param msg String to be written.
     * @throws DukeException If I/O Error occurs.
     */
    public void writeFile(String msg) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(msg);
            fw.close();
        } catch (IOException e) {
            throw new DukeException(" ☹ I/O Error!");
        }
    }

    /**
     * Appends a String to the end of an external file.
     *
     * @param task Task object - its String representation to be appended.
     * @throws DukeException If I/O Error occurs.
     */
    public void appendFile(Task task) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(task.stringToFile() + "\n");
            fw.close();
        } catch (IOException e) {
            throw new DukeException(" ☹ I/O Error!");
        }
    }

    /**
     * Creates an ArrayList of Task objects from an external file.
     *
     * @return An ArrayList of Task objects.
     * @throws DukeException If loading error occurs.
     */
    public ArrayList<Task> createList() throws DukeException {
        try {
            ArrayList<Task> taskList = new ArrayList<>();
            Scanner sc = new Scanner(loadFile());
            return convertFileToList(sc, taskList);
        } catch (IOException e) {
            throw new DukeException(" ☹ Loading error!");
        }
    }

    /**
     * Converts content in File to an ArrayList of Task objects.
     *
     * @param sc Scanner with read input.
     * @param taskList Empty taskList.
     * @return Filled taskList.
     * @throws DukeException If any error occurs.
     */
    private ArrayList<Task> convertFileToList(Scanner sc, ArrayList<Task> taskList) throws DukeException {
        while (sc.hasNext()) {
            String[] temp = sc.nextLine().split(" \\| ");
            assert (temp[1].strip().equals("0") | temp[1].strip().equals("1"));
            switch (temp[0].strip()) {
            case "T":
                taskList.add(new ToDo(temp[2].strip()));
                break;
            case "D":
                taskList.add(new Deadline(temp[2].strip(), temp[3].strip()));
                break;
            case "E":
                taskList.add(new Event(temp[2].strip(), temp[3].strip(), temp[4].strip()));
                break;
            default:
                assert false;
                break;
            }
            if (temp[1].strip().equals("1")) {
                taskList.get(taskList.size() - 1).markIsDone();
            }
        }
        return taskList;
    }
}
