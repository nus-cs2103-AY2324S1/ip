import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
public class Storage {
    private String filePath;

    private static final String FOLDER_PATH = "./data";

    private static final String DATA_FILE_PATH = "./data/duke.txt";

    public Storage() {
        this.filePath = DATA_FILE_PATH;
    }

    public ArrayList<Task> loadTask() throws IOException {
        ArrayList<Task> loadedTasks = new ArrayList<>();


        File folder = new File(FOLDER_PATH);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File file = new File(filePath);

        if (!file.exists()) {
            return loadedTasks;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                try {
                    String[] parts = line.split("]", 3);

                    String taskType = parts[0];
                    boolean isDone = parts[1].equals("[X");
                    String description = parts[2];
                    description = description.replace("(", "");
                    description = description.replace(")", "");
                    Task task = parseTask(taskType, description, isDone);
                    loadedTasks.add(task);
                } catch (RuntimeException e) {
                    System.err.println("Corrupted data!");
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading data file: " + e.getMessage());
        }

        return loadedTasks;
    }

    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        File folder = new File(FOLDER_PATH);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(filePath);

        for (int i = 0; i < tasks.size(); i++) {
            fw.write((tasks.get(i)).toSave() + "\n");
        }

        fw.close();

    }

    public static Task parseTask(String taskType, String taskDetails, Boolean isDone) {
        if (taskType.equalsIgnoreCase("[T")) {
            taskDetails = taskDetails.replace(" ", "");
            return new ToDoTask(taskDetails, isDone);
        } else if (taskType.equals("[D")) {
            return DeadlineTask.parseDeadline(taskDetails, isDone);
        } else if (taskType.equals("[E")) {
            return EventTask.parseEvent(taskDetails, isDone);
        } else {
            return null;
        }
    }
}
