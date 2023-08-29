package duke.helper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import duke.task.DukeException;
import duke.task.*;



public class Storage {
    /**
     * filePath stores where the actual file used for local storage is at
     * tempPath stores where the temporary file is at
     */

    String filePath;
    String tempPath;

    /**
     * Constructor for filePath is where the local storage is stored at,
     * parsed in initially in MeowBot
     * @param filePath
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.tempPath = "src/main/data/temp.txt";
    }

    /**
     *
     * @return returns the TaskList of tasks stored in the txt file
     * @throws FileNotFoundException when the filePath is not valid
     * @throws DukeException when generating the task from String
     */

    public ArrayList<Task> load() throws FileNotFoundException, DukeException {
        ArrayList<Task> lst = new ArrayList<>();
        Scanner sc = new Scanner(new File(filePath));
        int count = 0;
        while (sc.hasNextLine()){
            Task generatedTask = generateTaskFromString(sc.nextLine());
            lst.add(generatedTask);
            count += 1;
        }
        if (count == 0) System.out.println("Meow! A new User yay");

        else {
            System.out.println("Meow! Successfully loaded " + count + " tasks from previous session");
        }
        return lst;
    }

    /**
     * Generate the task from its String format
     * @param taskname name of the task stored in txt
     * @return generates the task from its string format
     * @throws DukeException error if unable to generate the Task from String
     */
    public Task generateTaskFromString(String taskname) throws DukeException {
        // check the taskname and its type
        Task generatedTask = null;
        String[] arr = taskname.split("\\|");
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
        if (mark.equals("1")) generatedTask.markCompleted();
        else if (mark.equals("0")) generatedTask.markUncompleted();

        return generatedTask;

    }

    /**
     * saves the TaskList tasks to its String format
     * @param tasks TaskList that contains the tasks for meowbot
     * @throws IOException when unable to write to the txt file
     */

    public void save(TaskList tasks) throws IOException {
//        System.out.println("Attempting to save to file");
        FileWriter tempwriter = new FileWriter(tempPath, true);  // Open the file in append mode
        tempwriter.write(tasks.totxtformat());
        tempwriter.close();
        File ogfile = new File(filePath);
        File temp = new File(tempPath);
        ogfile.delete();
        temp.renameTo(new File(filePath));
    }

    /**
     * creates a new file for the user if unable to find the data file
     * @throws IOException when unable to make the file at specified folderpath
     */
    public void createNewFile() throws IOException {
        String folderpath = "src/main/data";
        File folder = new File(folderpath);
        File file = new File(filePath);
        folder.mkdirs();
        file.createNewFile();
        System.out.println("Meow gotchu! Making local storage to remember your taskzz!");

    }
}
