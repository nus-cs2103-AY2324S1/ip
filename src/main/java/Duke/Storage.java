package Duke;

import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private static final String FILE_PATH = "./data/gman.txt"; //hardcoded
    private static final String DIR_PATH = "./data";



    public static ArrayList<Task> readTasks() throws FileNotFoundException {
        try {
            File file = new File(FILE_PATH);
            Scanner fileScanner = new Scanner(file);
            ArrayList<Task> tasksFromFile = new ArrayList<>();
            while (fileScanner.hasNext()) {
                Task taskRead = Task.readFromFile(fileScanner.nextLine());
                tasksFromFile.add(taskRead);
            }
            return tasksFromFile;
        } catch (FileNotFoundException | GmanException e) {
            System.out.println("Looks like you do not have any previous tasks saved!");
            return new ArrayList<Task>();
        }
    }

    public static void writeTasks(ArrayList<Task> tasks) throws IOException {
        try {
            File dir = new File(DIR_PATH);
            if (!dir.exists()) {
                dir.mkdir();
            }
            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                writer.write(task.toWriteString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Ohno. There was an error saving your files!");
        }
    }

}
