import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<String> loadTasks() {
        ArrayList<String> taskStrings = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                taskStrings.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            Ui.showMessage("Data file not found, starting with an empty task list.");
        }
        return taskStrings;
    }

    public void saveTasks(ArrayList<String> taskStrings) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (String taskString : taskStrings) {
                writer.println(taskString);
            }
        } catch (IOException e) {
            Ui.showError("Error saving tasks to file: " + e.getMessage());
        }
    }
}
