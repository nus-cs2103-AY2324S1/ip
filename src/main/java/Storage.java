import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static String DEFAULT_STORAGE_FILEPATH;
    public final Path path;

    public Storage(String filePath) throws DukeException {
        DEFAULT_STORAGE_FILEPATH = filePath;
        path = Paths.get(filePath);
        if (!isValidPath(path)) {
            throw new DukeException("Storage file should end with '.txt'");
        }
    }

    private static boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }

    public static void save(Task task) throws IOException {
        FileWriter fw = new FileWriter(DEFAULT_STORAGE_FILEPATH, true);
        fw.write(task.toWrite());
        fw.close();
    }

    public static void save(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(DEFAULT_STORAGE_FILEPATH);
        for (Task task: tasks.getList())
            fw.write(task.toWrite());
        fw.close();
    }

    public ArrayList<Task> load() throws FileNotFoundException, ParseException, DukeException {
        File file = new File(DEFAULT_STORAGE_FILEPATH);
        Scanner scanner = new Scanner(file);
        ArrayList<Task> taskList = new ArrayList<>();
        while (scanner.hasNext()) {
            Task task = Parser.parseTask(scanner.nextLine());
            taskList.add(task);
        }
        return taskList;
    }
}
