import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class Storage {
    //path for file to read/right to
    private static final String FILE_PATH = "./data/duke.txt";

    public static void writeTasksToFile(ArrayList<Task> taskList) throws IOException {
        //check if file path exists already or not
        File dir = new File("./data");
        if (!dir.exists()) {
            dir.mkdir();
        }
        FileWriter fw =  new FileWriter(FILE_PATH);
        for (Task task : taskList) {
            fw.write(task.writeFileFormat() + "\n");
        }
        fw.close();
    }
    public static ArrayList<Task> readTasksFromFile() throws FileNotFoundException {
        File file = new File(FILE_PATH);
        Scanner scanner = new Scanner(file);
        ArrayList<Task> savedTasks = new ArrayList<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            Task task = Task.readFromFile(line);
            if (task != null) {
                savedTasks.add(task);
            }
        }
        return savedTasks;
    }
    }
