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
    public static void addTask(String cmd) throws InvalidCommand, EmptyTaskException {
        String type = cmd.split("\\s+")[0]; // grab the first word
        Task newTask;
        switch (type) {
            case "todo":
                newTask = ToDo.interpret(cmd);
                break;
            case "deadline":
                newTask = Deadline.interpret(cmd);
                break;
            case "event":
                newTask = Event.interpret(cmd);
                break;
            default:
                throw new InvalidCommand(cmd);
        }
        taskList.add(newTask);
        numOfTasks++;
        // print statement
        /*Haste.printLine();
        System.out.println(Haste.INDENT + "Got it. I've added this task:");
        System.out.println(Haste.INDENT + newTask);
        System.out.println(Haste.INDENT + "Now you have " + getNumOfTasks() + " tasks in the list.");
        Haste.printLine(); */
    }

    public static void deleteTask(int id) {
        taskList.remove(id);
        numOfTasks--;
    }

    public static int getNumOfTasks() {
        return numOfTasks;
    }

    public static void printList() {
        Haste.printLine();
        for (Task a : taskList) {
            System.out.println(Haste.INDENT + (taskList.indexOf(a) + 1) + ". " + a);
        }
        Haste.printLine();
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
        try {
            File file = new File("./Data.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String cmd = scanner.nextLine();
                addTask(cmd);
            }
            file.delete();

        } catch (FileNotFoundException e) {
            // if file not found, just continue
            e.printStackTrace();
        } catch (InvalidCommand e) {
            e.printStackTrace();
        } catch (EmptyTaskException e) {
            e.printStackTrace();
        }
    }

    public static Task getTask(int id) {
        return taskList.get(id);
    }
}
