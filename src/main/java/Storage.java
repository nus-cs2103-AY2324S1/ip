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

    public ArrayList<Task> loadTask() throws DukeException {
        ArrayList<Task> loadedTasks = new ArrayList<>();

        try {
            File folder = new File(FOLDER_PATH);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            File file = new File(filePath);
            if (!file.exists()) {
                return loadedTasks;
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("]", 3);
                String taskType = parts[0];
                boolean isDone = parts[1].equals("[X");
                String description = parts[2];
                description = description.replace("(", "");
                description = description.replace(")", "");
                Task task = Parser.parse(taskType, description, isDone);
                loadedTasks.add(task);
            }
            scanner.close();
        } catch (IOException e) {
            throw new DukeException("Corrupted data file: " + e.getMessage());
        }
        return loadedTasks;
    }


    public void saveTasks(ArrayList<Task> tasks) throws DukeException {
        try {
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
        } catch (IOException e) {
            throw new DukeException("Error saving tasks to data file: " + e.getMessage());
        }
    }
}
