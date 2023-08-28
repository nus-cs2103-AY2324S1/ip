import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String taskListPath;

    public Storage(String taskListPath) {
        this.taskListPath = taskListPath;
    }

    public ArrayList<String> getTaskDetails() {
        File taskFile = new File(this.taskListPath);
        ArrayList<String> taskDetails = new ArrayList<>();
        try {
            taskFile.createNewFile();
            Scanner scanner = new Scanner(taskFile);
            while (scanner.hasNextLine()) {
                String task = scanner.nextLine();
                taskDetails.add(task);
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            Ui.printError("Task list not found!");
        } catch (IOException ex) {
            Ui.printError("Task list file creation failed!");
        }
        return taskDetails;
    }
    public void saveTasks(ArrayList<Task> taskList) {
        try {
            FileWriter writer = new FileWriter("tasks.txt", false);
            for (int i = 0; i < taskList.size(); i++) {
                writer.write(taskList.get(i).toSaveFormat());
            }
            writer.close();
        } catch (IOException e) {
            Ui.printError("Saving tasks failed.\n");
            e.printStackTrace();
        }
    }
}
