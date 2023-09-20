package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class encapsulates dealing with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    private final String filePath;

    /**
     * Constructor for Storage.
     *
     * @param filePath the path of the file to be opened.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private void createDirectory() {
        File f = new File("./data");
        if (!hasDirectory() && !f.mkdir()) {
            return;
        }
    }

    private boolean hasDirectory() {
        File f = new File("./data");
        return f.exists() && f.isDirectory();
    }

    private boolean createFile() throws DukeException {
        File f = new File(filePath);
        try {
            return f.createNewFile();
        } catch (IOException e) {
            throw new DukeException("HEYHEY! Seems like I couldn't create the file for you. "
                    + "Please manually add the file!");
        }
    }
    private Scanner getFile() throws DukeException {
        File f = new File(filePath);
        try {
            return new Scanner(f);
        } catch (FileNotFoundException e) {
            throw new DukeException("HMMMMMM! The file should be created for you but there could be an issue. "
                    + "Please manually add the file!");
        }
    }

    private LocalDateTime undoDateFormatInputFile(String lineInFile) {
        return LocalDateTime.parse(lineInFile, DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma"));
    }

    /**
     * Returns an array list that contains the tasks being loaded from the file.
     *
     * @return an array list containing the tasks retrieved from the saved file.
     */
    public ArrayList<Task> loadFile() {
        createDirectory();
        createFile();

        assert new File(filePath).exists();

        return goThroughFile(getFile());

    }

    /**
     * Appends a new line into the file based off the latest task added into the task list.
     *
     * @param taskList the corresponding task list to read the tasks from.
     * @throws DukeException if the attempt to write to the file fails.
     */
    public void addLineToFile(TaskList taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            if (taskList.taskArray.size() == 1) {
                fw.write(taskList.taskArray.get(0).convertToSaveFormat());
            } else {
                fw.write("\n" + taskList.taskArray.get(taskList.taskArray.size() - 1).convertToSaveFormat());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("UHOH! Something went wrong when attempting to write to file!");
        }
    }

    /**
     * Rewrites the entire file based off the changes made in the task list.
     *
     * @param taskList the corresponding task list to read the tasks from.
     * @throws DukeException if the attempt to write to the file fails.
     */
    public void rewriteFile(TaskList taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < taskList.taskArray.size(); i++) {
                if (i != taskList.taskArray.size() - 1) {
                    fw.write(taskList.taskArray.get(i).convertToSaveFormat() + "\n");
                } else {
                    fw.write(taskList.taskArray.get(i).convertToSaveFormat());
                }
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("UHOH! Something went wrong when attempting to write to file!");
        }
    }

    public ArrayList<Task> goThroughFile(Scanner s) {
        ArrayList<Task> taskArray = new ArrayList<>();
        while (s.hasNext()) {
            String[] line = s.nextLine().trim().split(" \\| ", 3);

            switch (line[0]) {
            case "T":
                taskArray.add(new Todo(line[2]));
                break;
            case "D":
                String[] remainLine = line[2].split(" \\| ", 2);
                taskArray.add(new Deadline(remainLine[0], undoDateFormatInputFile(remainLine[1])));
                break;
            case "E":
                String[] remainingLine = line[2].split(" \\| ", 2);
                String[] getDateTime = remainingLine[1].split(" to ");
                taskArray.add(new Event(remainingLine[0],
                        undoDateFormatInputFile(getDateTime[0]), undoDateFormatInputFile(getDateTime[1])));
                break;
            default:
                // There will be no default case as the file can only take on the 3 values above
            }

            if (line[1].equals("X")) {
                taskArray.get(taskArray.size() - 1).markDone();
            }
        }
        s.close();
        return taskArray;
    }
}
