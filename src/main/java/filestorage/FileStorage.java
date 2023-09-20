package filestorage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import dukeexception.DukeException;
import list.TaskList;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

/**
 * Represents a class that will convey the user input to a file and also the other way around.
 */
public class FileStorage {

    private final File fileData;

    public FileStorage(String filePath) {
        this.fileData = new File(filePath);
    }

    /**
     * Writes all the data from the taskList to the file.
     *
     * @param userList The List that contains all the tasks by the user.
     * @throws DukeException If there is any situation where the FileWriter fails to write.
     */
    public void write(TaskList userList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(fileData);
            for (int i = 0; i < userList.size(); i++) {
                fw.write(userList.get(i).toString());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Writing Error");
        }
    }

    /**
     * Reads all the information on a textFile and load it back into the program
     *
     * @return ArrayList The list of all the task by user.
     * @throws DukeException If the content on the file cannot be recognised.
     */
    public ArrayList<Task> read() throws DukeException {
        try {
            ArrayList<Task> dataList = new ArrayList<>();
            Scanner scanner = new Scanner(fileData);
            while (scanner.hasNext()) {
                Task task = new Task();
                String inputs = scanner.nextLine();
                String info = inputs.substring(9);
                assert !inputs.trim().startsWith("[ ]") : "There should be a allocated type for the task";
                if (inputs.trim().startsWith("[T]")) {
                    task = new Todo(info);
                } else if (inputs.trim().startsWith("[D]")) {
                    String[] split = info.split("\\(by: |\\)");
                    task = new Deadline(split[0], split[1]);
                } else if (inputs.trim().startsWith("[E]")) {
                    String[] split = info.split("\\(from: | to: |\\)");
                    task = new Event(split[0], split[1], split[2]);
                }
                isTaskMarked(task, inputs);
                if (!task.isItEmpty()) {
                    dataList.add(task);
                }
            }
            scanner.close();
            return dataList;
        } catch (FileNotFoundException e) {
            throw new DukeException("Invalid file");
        }
    }

    private static void isTaskMarked(Task task, String inputs) {
        if (inputs.substring(6).startsWith("X")) {
            task.markDone();
        }
    }
}
