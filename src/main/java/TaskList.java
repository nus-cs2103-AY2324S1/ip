import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class TaskList {
    public static final ArrayList<Task>  taskList = new ArrayList<>();
    // number tracker
    public static int numOfTasks = 0;
    // adds task into task list
    public static void addTask(Task newTask) throws InvalidCommand, EmptyTaskException {
        taskList.add(newTask);
        numOfTasks++;
    }

    public static void deleteTask(int id) {
        taskList.remove(id);
        numOfTasks--;
    }

    public static int getNumOfTasks() {
        return numOfTasks;
    }


    // save method
    public static void save() {
        try {
            FileWriter writer = new FileWriter("./Data.txt");
            for (Task task: taskList) {
                writer.write(task.getCmd() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // read method
    public static void read() {
        File file = new File("./Data.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static Task getTask(int id) {
        return taskList.get(id);
    }
}
