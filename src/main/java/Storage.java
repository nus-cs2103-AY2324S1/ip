import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }


    public static void saveTasks(TaskList taskList) {
        try {
            //Solution below inspired by https://www.geeksforgeeks.org/io-bufferedwriter-class-methods-java/
            FileWriter file = new FileWriter(filePath);
            BufferedWriter writer = new BufferedWriter(file);
            for (int i = 0; i < taskList.getSize(); i++) {
                Task task = taskList.getTask(i);
                writer.write(task.fileString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("☹ OOPS!!! Error when saving task(s).");
        }
    }

    public static ArrayList<Task> loadTasks() { //change type
        ArrayList<Task> taskList =  new ArrayList<>();
        try {
            File loadedFile = new File(filePath);
            Scanner sc = new Scanner(loadedFile);
            while (sc.hasNextLine()) {
                String nextLine = sc.nextLine();
                Parser.readLine(nextLine, taskList);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("☹ OOPS!!! Error when loading task(s).");
        }
        return taskList;
    }
}
