import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

//deals with loading tasks from the file and saving tasks in the file
public class Storage {

    //load tasks from the file into the chatbot
    public static ArrayList<Task> loadTasks() throws FileNotFoundException {
        ArrayList<Task> dukeList = new ArrayList<>();
        File file = new File("duke.txt");
        if (file.exists()) {
            try (Scanner fileScanner = new Scanner(file)) {
                while (fileScanner.hasNextLine()) {
                    String task = fileScanner.nextLine();
                    Task dtask = Taskparser.parseTask(task); //convert format of tasks in file to list
                    dukeList.add(dtask);
                }
            }
        }
        return dukeList;
    }

    // Save tasks to a data file
    public static void saveTasks(ArrayList<Task> list) {
        File file = new File("duke.txt");
        try (PrintWriter out = new PrintWriter("duke.txt")) {
            for (Task task : list) {
                String taskString = Taskparser.taskToString(task); //convert task to string
                out.println(taskString);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}
