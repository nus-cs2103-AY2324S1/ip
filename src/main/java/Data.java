import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Consumer;

public class Data {
    private static String DATA_DIRECTORY = "./";
    private static String DATA_FILE = "data.duchess";

    /**
     * Creates an empty save file to store the tasks in. This save file is specified by DATA_DIRECTORY and DATA_FILE.
     */
    public static void createSaveFile() {
        try {
            File file = new File(DATA_DIRECTORY + DATA_FILE);

            // Create a new file if it doesn't already exist.
            file.createNewFile();

        } catch(IOException e) {
            System.out.println(String.format("An error has occurred: %s", e.getMessage()));
        }
    }

    /**
     * Saves the specified ArrayList of tasks to the data file specified by DATA_DIRECTORY and DATA_FILE.
     * If the file does not exist, an error will occur.
     *
     * @param tasks - the ArrayList of tasks to be saved.
     */
    public static void saveTasksToFile(ArrayList<Task> tasks) {
        try {
            File file = new File(DATA_DIRECTORY + DATA_FILE);

            FileWriter fileWriter = new FileWriter(file);

            for (int i = 0; i < tasks.size(); i++) {
                fileWriter.write(tasks.get(i).toSaveString());

                // Add a newline unless I just wrote the last item.
                if (i != tasks.size() - 1) {
                    fileWriter.write("\n");
                }
            }

            fileWriter.close();

        } catch (IOException e) {
            System.out.println(String.format("An error has occurred: %s", e.getMessage()));
        }
    }

    /**
     * Loads the specified ArrayList of tasks to the data file specified by DATA_DIRECTORY and DATA_FILE.
     *
     * @param tasks - the ArrayList for tasks to be loaded into. Any existing tasks in this arraylist will be overridden.
     */
    public static void loadTasksFromFile(ArrayList<Task> tasks) {
        try {
            File file = new File(DATA_DIRECTORY + DATA_FILE);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                Task task = Task.parseSaveString(sc.nextLine());

                if (task != null) {
                    tasks.add(task);
                }
            }

            sc.close();

        } catch(IOException e) {
            System.out.println(String.format("An error has occurred: %s", e.getMessage()));
        }
    }
}
