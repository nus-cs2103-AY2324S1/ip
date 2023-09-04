package duke.io;

import duke.task.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Saver {

    private static final String FILE_PATH = "./data/duke.txt";

    public static void saveToFile(List<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                fw.write(task.toFileFormat() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
