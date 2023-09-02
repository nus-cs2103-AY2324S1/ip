import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void createFile() {
        try {
            // Create if it doesn't exist
            Files.createDirectories(Paths.get("./data/"));
            File dataFile = new File("./data/duke.txt");
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating data folder or file.");
        }
    }

    public static ArrayList<Task> retrieveData() {
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            File file = new File("./data/duke.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String dataLine = scanner.nextLine();
                Task task = Parser.dataToTask(dataLine);
                taskList.add(task);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Creating a new file.");
        }

        return taskList;
    }

    public static void save(ArrayList<Task> taskList) {
        try {
            FileWriter writer = new FileWriter("./data/duke.txt");

            for (Task task : taskList) {
                // Write each task with specified format
                writer.write(Parser.taskData(task) + "\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }


}
