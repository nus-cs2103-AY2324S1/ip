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

    String filePath;
    String tempPath;
    public Storage(String filePath) {
        this.filePath = filePath;
        this.tempPath = "src/main/data/temp.txt";
    }

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

    Task generateTaskFromString(String taskname) throws DukeException {
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

    public void createNewFile() throws IOException {
        String folderpath = "src/main/data";
        File folder = new File(folderpath);
        File file = new File(filePath);
        folder.mkdirs();
        file.createNewFile();
        System.out.println("Meow gotchu! Making local storage to remember your taskzz!");

    }
}
