import java.util.ArrayList;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
public class TaskWriter {
    public static void writeTasksToFile(ArrayList<Tasks> tasksList, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Tasks task : tasksList) {
                // Convert the task to a string representation and write it to the file
                String taskString = task.toString();
                writer.write(taskString);
                writer.newLine(); // Add a newline character to separate tasks
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
}