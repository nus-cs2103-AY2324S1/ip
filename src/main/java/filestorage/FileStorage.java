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
     * A method that will write all the data from the tasklist to the file.
     *
     * @param userList contains all the tasks by the user.
     * @throws DukeException if there is any situation where the FileWriter fails to write.
     */
    public void write(TaskList userList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(this.fileData);
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
     * A method that will read all the information on a textFile and load it back into the program
     *
     * @return ArrayList a list of all the task by user.
     * @throws DukeException if the content on the file cannot be recognised.
     */
    public ArrayList<Task> read() throws DukeException {
        try {
            ArrayList<Task> dataList = new ArrayList<>();
            Scanner scanner = new Scanner(this.fileData);
            Task task;
            //System.out.println("reading");
            while (scanner.hasNext()) {
                String inputs = scanner.nextLine();
                if (inputs.startsWith("  [T]")) {
                    String info = inputs.substring(9);
                    task = new Todo(info);
                    if (inputs.substring(6).startsWith("X")) {
                        task.markDone();
                    }
                    dataList.add(task);
                } else if (inputs.startsWith("  [D]")) {
                    String info = inputs.substring(9);
                    String[] split = info.split("\\(by: |\\)");
                    task = new Deadline(split[0], split[1]);
                    if (inputs.substring(6).startsWith("X")) {
                        task.markDone();
                    }
                    dataList.add(task);
                } else if (inputs.startsWith("  [E]")) {
                    String info = inputs.substring(9);
                    String[] split = info.split("\\(from: | to: |\\)");
                    task = new Event(split[0], split[1], split[2]);
                    if (inputs.substring(6).startsWith("X")) {
                        task.markDone();
                    }
                    dataList.add(task);
                }
            }
            scanner.close();
            //System.out.println(count);
            return dataList;
        } catch (FileNotFoundException e) {
            throw new DukeException("Invalid file");
        }
    }

}
