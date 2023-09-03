package duke.helper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.DukeException;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Storage is the local class that deals with the logic for storing and updating tasks data locally
 */


public class Storage {
    /**
     * Storage is the local class that deals with the logic for storing tasks data locally
     * filePath stores where the actual file used for local storage is at
     * tempPath stores where the temporary file is at
     */

    private final String FILE_PATH;
    private final String TEMP_PATH;

    /**
     * Constructor for filePath is where the local storage is stored at,
     * parsed in initially in MeowBot
     */
    public Storage(String filePath) {
        this.FILE_PATH = filePath;
        this.TEMP_PATH = "src/main/data/temp.txt";
    }

    /**
     * @return returns the TaskList of tasks stored in the txt file
     * @throws FileNotFoundException when the filePath is not valid
     * @throws DukeException when generating the task from String
     */

    public ArrayList<Task> load() throws FileNotFoundException, DukeException {
        ArrayList<Task> lst = new ArrayList<>();
        Scanner sc = new Scanner(new File(this.FILE_PATH));
        int count = 0;
        while (sc.hasNextLine()) {
            Task generatedTask = generateTaskFromString(sc.nextLine());
            lst.add(generatedTask);
            count += 1;
        }
        if (count == 0) {
            System.out.println("Meow! A new User yay");
        } else {
            System.out.println("Meow! Successfully loaded " + count + " tasks from previous session");
        }
        return lst;
    }

    /**
     * Generate the task from its String format
     * @param taskName name of the task stored in txt
     * @return generates the task from its string format
     * @throws DukeException error if unable to generate the Task from String
     */
    public Task generateTaskFromString(String taskName) throws DukeException {
        // check the taskname and its type
        Task generatedTask = null;
        String[] arr = taskName.split("\\|");
        int length = arr.length;

        String ogname = arr[3];
        String tasktype = arr[2];
        String result = arr[1];
        String mark = arr[0];

        if (tasktype.equals("Event")) {
            generatedTask = new Event(ogname);
        } else if (tasktype.equals("Deadline")) {
            generatedTask = new Deadline(ogname);
        } else if (tasktype.equals("Todo")) {
            generatedTask = new Todo(ogname);
        }

        if (mark.equals("1")) {
            generatedTask.markCompleted();
        } else if (mark.equals("0")) {
            generatedTask.markUncompleted();
        }

        return generatedTask;

    }

    /**
     * saves the TaskList tasks to its String format to the txt file
     * @param tasks TaskList that contains the tasks for meowbot
     * @throws IOException when unable to write to the txt file
     */

    public void save(TaskList tasks) throws IOException {
        FileWriter tempWriter = new FileWriter(this.TEMP_PATH, true);
        // Open the file in append mode
        tempWriter.write(tasks.totxtformat());
        tempWriter.close();
        File ogFile = new File(this.FILE_PATH);
        File temp = new File(this.TEMP_PATH);
        ogFile.delete();
        temp.renameTo(new File(this.FILE_PATH));
    }

    /**
     * creates a new file for the user if unable to find the data file
     * @throws IOException when unable to make the file at specified folder path
     */
    public void createNewFile() throws IOException {
        String folderPath = "src/main/data";
        File folder = new File(folderPath);
        File file = new File(this.FILE_PATH);
        folder.mkdirs();
        file.createNewFile();
        System.out.println("Meow gotchu! Making local storage to remember your taskzz!");

    }
}
