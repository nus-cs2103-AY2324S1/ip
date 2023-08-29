import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
public class TaskFileHandler {
    private static final String FILE_PATH = "./data/bouncy.txt";

    public static void saveTasksToDisk(ArrayList<Task> database) {
        StringBuilder builder = new StringBuilder();
        for(Task task : database) {
            builder.append(task.toFileFormat()).append("\n");
        }
        File file = new File(FILE_PATH);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            System.out.println(builder);
            writer.write(builder.toString());
            writer.flush();
            System.out.println("Written to hard disk");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void loadTasksFromDisk(ArrayList<Task> database) {
    }
}
