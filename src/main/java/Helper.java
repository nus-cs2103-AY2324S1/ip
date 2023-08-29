import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Helper {
    public static void saveToFile(String filename, ArrayList<Task> list) {
        Path path = Paths.get("..", "data");
        if (Files.notExists(path)) {
            //if the directory not exist, create one instantly
            //duke.txt will be created automatically by default
            try {
                Files.createDirectories(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try (FileWriter filewriter = new FileWriter(filename)) {
            for (Task task : list) {
                filewriter.write(task.stringInFile() + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
