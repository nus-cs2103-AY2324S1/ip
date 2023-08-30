import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
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
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("No saved tasks found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                String type = parts[0].trim();
                boolean isDone = parts[1].trim().equals("1");
                String name = parts[2].trim();

                Task task = null;
                switch (type) {
                    case "T":
                        task = new ToDos(name);
                        break;
                    case "D":
                        String datetime = parts[3].trim();
                        task = new Deadlines(name, datetime);
                        break;
                    case "E":
                        String from = parts[3].trim();
                        String to = parts[4].trim();
                        task = new Events(name, from, to);
                        break;
                }
                if (task != null) {
                    if (isDone) task.setDone();
                    database.add(task);
                }
            }
            System.out.println("Tasks loaded from disk.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
