import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileUtils {

    private static final String FILE_PATH = "./data/duke.txt";

    public static void saveTasksToFile(ArrayList<Task> tasksToSave) throws IOException {
        File directory = new File("./data");
        if (!directory.exists()) {
            directory.mkdir();
        }

        FileWriter fw = new FileWriter(FILE_PATH);
        for (Task task : tasksToSave) {
            fw.write(task.toFileFormat() + "\n");
        }
        fw.close();

    }

    public static ArrayList<Task> loadTasksFromFile() throws FileNotFoundException {
        File file = new File(FILE_PATH);

        Scanner scanner = new Scanner(file);

        ArrayList<Task> result = new ArrayList<>();

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            Task task = Task.fromFileFormat(line);
            if (task != null) {
                result.add(task);
            }
        }
        return result;
    }

}
